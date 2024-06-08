package ru.kazenin.cashezavr.outside.tsukasa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.kazenin.cashezavr.outside.service.ReceiptLoadService;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadReceiptTask {

    private final RedissonClient redissonClient;
    private final ReceiptLoadService receiptLoadService;

    @Async("loadExecutor")
    public void load(UUID receiptUuid) {
        var receiptLoadLock = redissonClient.getFairLock(receiptUuid.toString());
        if (!receiptLoadLock.tryLock()) {
            return;
        }
        try {
            log.debug("Попытка выгрузки чека {}", receiptUuid);
            receiptLoadService.loadReceipt(receiptUuid);
            log.debug("Конец выгрузки чека {}", receiptUuid);
        } finally {
            receiptLoadLock.unlock();
        }
    }

}
