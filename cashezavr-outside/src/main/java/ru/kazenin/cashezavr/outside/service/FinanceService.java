package ru.kazenin.cashezavr.outside.service;

import ru.kazenin.cashezavr.common.entity.ReceiptEntity;

public interface FinanceService {
    void processReceipt(ReceiptEntity receipt);
}
