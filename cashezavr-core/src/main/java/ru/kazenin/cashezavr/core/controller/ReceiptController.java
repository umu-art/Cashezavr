package ru.kazenin.cashezavr.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.kazenin.api.ReceiptApi;
import ru.kazenin.cashezavr.core.service.ReceiptService;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReceiptController implements ReceiptApi {

    private final ReceiptService receiptService;

    @Override
    public ResponseEntity<ReceiptDto> loadReceipt(ReceiptRequestDto receiptRequestDto) {
        return receiptService.loadReceipt(receiptRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(201).build());
    }

    @Override
    public ResponseEntity<List<ReceiptDto>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }
}
