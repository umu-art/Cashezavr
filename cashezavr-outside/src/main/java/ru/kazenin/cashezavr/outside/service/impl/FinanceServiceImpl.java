package ru.kazenin.cashezavr.outside.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kazenin.cashezavr.common.entity.PromotionEntity;
import ru.kazenin.cashezavr.common.entity.ReceiptEntity;
import ru.kazenin.cashezavr.outside.jpa.PromotionJpa;
import ru.kazenin.cashezavr.outside.service.FinanceService;

import java.time.OffsetDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    @Value("${cherry.baseCashBack}")
    private double baseCashBack;

    private final PromotionJpa promotionJpa;

    @Override
    public void processReceipt(ReceiptEntity receipt) {
        var sum = receipt.getReceiptItems()
                .stream().mapToDouble(receiptItem ->
                        Math.max(baseCashBack, findMaxPromotionForProduct(receiptItem.getName())) *
                                receiptItem.getPrice() *
                                receiptItem.getCount() / 100).sum();

        receipt.setReturnSum(sum);
        receipt.getClient().setActualBalance(receipt.getClient().getActualBalance() + sum);
        receipt.getClient().setAllBalance(receipt.getClient().getAllBalance() + sum);
    }

    /**
     * Ищет максимальный кэшбэк на данный товар среди акций, если не нашли, то возвращает 0
     *
     * @param name наименование товара
     * @return максимальный акционный кэшбэк
     */
    private double findMaxPromotionForProduct(String name) {
        var promotions = promotionJpa.findAllByProductAndStartsBeforeAndEndsBefore(name,
                OffsetDateTime.now(),
                OffsetDateTime.now());

        return promotions.stream().mapToDouble(PromotionEntity::getPercent).max().orElse(0);
    }
}
