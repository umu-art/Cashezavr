package ru.kazenin.cashezavr.core.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.kazenin.cashezavr.common.dto.InfraNames;
import ru.kazenin.cashezavr.common.entity.ReceiptEntity;
import ru.kazenin.cashezavr.core.config.ReceiptPollParams;
import ru.kazenin.cashezavr.core.exception.ReceiptAlreadyExistsException;
import ru.kazenin.cashezavr.core.jpa.ReceiptJpa;
import ru.kazenin.cashezavr.core.mapper.ReceiptMapper;
import ru.kazenin.cashezavr.core.service.ContextHolder;
import ru.kazenin.cashezavr.core.service.ReceiptService;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptPollParams receiptPollParams;

    private final ReceiptJpa receiptJpa;
    private final ReceiptMapper receiptMapper;

    private final ContextHolder contextHolder;
    private final RabbitTemplate rabbitTemplate;

    private final EntityManager entityManager;

    @Override
    public Optional<ReceiptDto> loadReceipt(ReceiptRequestDto receiptRequestDto) {
        var receiptUuid = save(receiptRequestDto);

        notifyOutside();

        Date start = Calendar.getInstance().getTime();
        while (true) {
            var receipt = getIfReady(receiptUuid);
            if (receipt.isPresent()) {
                return receipt;
            }

            if (Calendar.getInstance().getTime()
                    .after(DateUtils.addMilliseconds(start, receiptPollParams.getPollMaxTime()))) {
                break; // Poll timeout
            }

            try {
                Thread.sleep(receiptPollParams.getPollWaitTime());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return Optional.empty();
    }

    /**
     * Сохраняет чек (пока пустой) в бд.
     *
     * @param receiptRequestDto данные для сохранения
     * @return uuid чека в нашей базе
     */
    private UUID save(ReceiptRequestDto receiptRequestDto) {
        if (receiptJpa.existsByQr(receiptRequestDto.getQr())) {
            throw new ReceiptAlreadyExistsException();
        }

        var entity = new ReceiptEntity();
        entity.setClient(contextHolder.getCurrentClient());
        entity.setQr(receiptRequestDto.getQr());

        var result = receiptJpa.saveAndFlush(entity);
        log.info("Новый чек {}", result);
        return result.getUuid();
    }

    /**
     * Отправляем сообщение о новом чеке
     */
    private void notifyOutside() {
        rabbitTemplate.convertAndSend(InfraNames.RECEIPT_LOAD_MESSAGES, "cherry");
    }

    /**
     * Проверяет выгружен ли чек из ФНС, и если да, то возвращает его.
     *
     * @param receiptUuid uuid чека.
     * @return optional с чеком (если выгружен)
     */
    private Optional<ReceiptDto> getIfReady(UUID receiptUuid) {
        if (receiptJpa.countByUuidAndLoadedNotNull(receiptUuid) == 1) {
            var entity = receiptJpa.findById(receiptUuid).orElseThrow();
            return Optional.of(receiptMapper.toDto(entity));
        }
        return Optional.empty();
    }

    @Override
    public List<ReceiptDto> getAllReceipts() {
        var entities = receiptJpa.findAllByClientOrderByDate(contextHolder.getCurrentClient());
        return receiptMapper.toDto(entities);
    }
}
