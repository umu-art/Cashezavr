package ru.kazenin.cherry.outside.service;

import ru.kazenin.cherry.common.entity.ReceiptEntity;

public interface FinanceService {
    void processReceipt(ReceiptEntity receipt);
}
