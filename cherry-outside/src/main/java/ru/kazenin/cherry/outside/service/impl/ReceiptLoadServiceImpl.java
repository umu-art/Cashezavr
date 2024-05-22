package ru.kazenin.cherry.outside.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.kazenin.cherry.outside.service.ReceiptLoadService;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptItemDto;
import ru.kazenin.model.ReceiptRequestDto;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptLoadServiceImpl implements ReceiptLoadService {

    public static final String ID = "id";
    private final RestClient ftsClient;

    /**
     * Выполняет выгрузку данных чека из ФНС
     *
     * @param requestDto данные для выгрузки
     * @return данные чека
     */
    @Override
    public ReceiptDto load(@NonNull ReceiptRequestDto requestDto) {
        log.info("attempt to load {}", requestDto);

        var uuid = getReceiptUuid(requestDto);
        var rowResult = getReceiptRaw(uuid);
        return parseReceiptRaw(rowResult);
    }

    /**
     * Получает uuid чека в системе ФНС
     *
     * @param requestDto данные для выгрузки
     * @return uuid чека
     */
    private String getReceiptUuid(ReceiptRequestDto requestDto) {
        var result = ftsClient.post()
                .uri("/v2/ticket")
                .body("{\"qr\": \"" + requestDto.getQr() + "\"}")
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
    private JsonNode getReceiptRaw(String receiptUuid) {
        return ftsClient.get()
                .uri("/v2/tickets/" + receiptUuid)
                .retrieve()
                .toEntity(JsonNode.class)
                .getBody()
                .get("ticket")
                .get("document")
                .get("receipt")
                .get("items");
    }

    /**
     * Парсит данные полученные с ФНС
     *
     * @param raw голые данные
     * @return дтоха с нашей структурой
     */
    private ReceiptDto parseReceiptRaw(JsonNode raw) {
        ReceiptDto result = new ReceiptDto();

        for (JsonNode itemRaw : raw) {
            ReceiptItemDto itemDto = new ReceiptItemDto()
                    .productName(itemRaw.get("name").asText())
                    .productCount(itemRaw.get("quantity").asInt())
                    .productPrice(BigDecimal.valueOf(itemRaw.get("price").asDouble() / 100));

            result.addReceiptItemsItem(itemDto);
        }

        return result;
    }
}
