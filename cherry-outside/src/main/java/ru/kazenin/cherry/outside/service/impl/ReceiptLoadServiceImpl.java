package ru.kazenin.cherry.outside.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.kazenin.cherry.common.entity.ReceiptEntity;
import ru.kazenin.cherry.common.entity.ReceiptItemEntity;
import ru.kazenin.cherry.outside.jpa.ReceiptJpa;
import ru.kazenin.cherry.outside.service.FinanceService;
import ru.kazenin.cherry.outside.service.OutsideAuthService;
import ru.kazenin.cherry.outside.service.ReceiptLoadService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptLoadServiceImpl implements ReceiptLoadService {

    private static final String ID = "id";
    private final RestClient ftsClient;

    private final ReceiptJpa receiptJpa;
    private final FinanceService financeService;
    private final OutsideAuthService outsideAuthService;

    @Override
    public void loadReceipt(UUID receiptUuid) {
        var receipt = receiptJpa.findById(receiptUuid).orElseThrow();

        if (isLoaded(receipt)) {
            return;
        }

        fillReceiptEntity(receipt);
        financeService.processReceipt(receipt);

        receiptJpa.saveAndFlush(receipt);
    }

    private boolean isLoaded(ReceiptEntity receipt) {
        return receipt.getLoaded() != null;
    }

    /**
     * Наполняет сущность чека данными из ФНС и проставляет дату выгрузки.
     *
     * @param receipt сущность для наполнения
     */
    private void fillReceiptEntity(ReceiptEntity receipt) {
        var fnsReceiptUuid = getFnsReceiptUuid(receipt.getQr());
        var fnsReceiptRow = getFnsReceiptRaw(fnsReceiptUuid);

        receipt.setReceiptItems(parseReceiptItems(fnsReceiptRow, receipt));
        receipt.setLoaded(OffsetDateTime.now());
    }

    /**
     * Получает uuid чека в системе ФНС
     *
     * @param qr данные для выгрузки
     * @return uuid чека
     */
    private String getFnsReceiptUuid(String qr) {
        var result = ftsClient.post()
                .uri("/v2/ticket")
                .header("sessionId", outsideAuthService.getSessionId())
                .body("{\"qr\": \"" + qr + "\"}")
                .retrieve()
                .toEntity(JsonNode.class);

        return Objects.requireNonNull(result.getBody()).get(ID).asText();
    }

    /**
     * Получает содержимое чека по uuid
     *
     * @param receiptUuid uuid чека
     * @return содержимое чека
     */
    private JsonNode getFnsReceiptRaw(String receiptUuid) {
        return ftsClient.get()
                .uri("/v2/tickets/" + receiptUuid)
                .header("sessionId", outsideAuthService.getSessionId())
                .retrieve()
                .toEntity(JsonNode.class)
                .getBody()
                .get("ticket")
                .get("document")
                .get("receipt")
                .get("items");
    }

    /**
     * Парсит данные полученные с ФНС в массив элементов чека
     *
     * @param raw голые данные из ФНС
     * @return массив элементов чека
     */
    private List<ReceiptItemEntity> parseReceiptItems(JsonNode raw, ReceiptEntity receipt) {
        var result = new ArrayList<ReceiptItemEntity>();

        for (JsonNode itemRaw : raw) {
            ReceiptItemEntity itemDto = new ReceiptItemEntity();
            itemDto.setName(itemRaw.get("name").asText());
            itemDto.setCount(itemRaw.get("quantity").asInt());
            itemDto.setPrice(itemRaw.get("price").asDouble() / 100);
            itemDto.setReceiptEntity(receipt);

            result.add(itemDto);
        }

        return result;
    }

}
