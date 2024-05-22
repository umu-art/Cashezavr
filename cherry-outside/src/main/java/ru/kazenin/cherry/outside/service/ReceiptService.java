package ru.kazenin.cherry.outside.service;

import org.springframework.lang.NonNull;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

public interface ReceiptService {

    /**
     * Выполняет выгрузку (см ReceiptLoadService) и сохранение в бд данных о чеке.
     *
     * @param requestDto данные для выгрузки
     * @return данные чека
     */
    ReceiptDto loadAndSave(@NonNull ReceiptRequestDto requestDto);
}
