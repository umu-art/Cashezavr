package ru.kazenin.cashezavr.core.service;

import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {

    Optional<ReceiptDto> loadReceipt(ReceiptRequestDto receiptRequestDto);

    List<ReceiptDto> getAllReceipts();
}
