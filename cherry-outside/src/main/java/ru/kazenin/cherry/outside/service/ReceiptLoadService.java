package ru.kazenin.cherry.outside.service;

import org.springframework.lang.NonNull;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

public interface ReceiptLoadService {

    /**
     * Выполняет выгрузку данных чека из ФНС
     *
     * @param requestDto данные для выгрузки
     * @return данные чека
     */
    ReceiptDto load(@NonNull ReceiptRequestDto requestDto);
}
