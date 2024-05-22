package ru.kazenin.cherry.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kazenin.cherry.core.jpa.ReceiptJpa;
import ru.kazenin.cherry.core.service.ContextHolder;
import ru.kazenin.cherry.core.service.ReceiptService;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptJpa receiptJpa;
    private final ContextHolder contextHolder;

    @Override
    public ReceiptDto loadReceipt(ReceiptRequestDto receiptRequestDto) {
        log.info("{}", receiptRequestDto);
        return null;
    }

    @Override
    public List<ReceiptDto> getAllReceipts() {
        log.info("{}", contextHolder.getCurrentUser());
        return null;
    }
}
