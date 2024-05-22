package ru.kazenin.cherry.core.service;

import org.springframework.http.ResponseEntity;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

import java.util.List;

public interface ReceiptService {

    ReceiptDto loadReceipt(ReceiptRequestDto receiptRequestDto);

    List<ReceiptDto> getAllReceipts();
}
