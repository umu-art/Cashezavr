package ru.kazenin.cashezavr.outside.tsukasa;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kazenin.cashezavr.cashezavr.dto.InfraNames;
import ru.kazenin.cashezavr.cashezavr.entity.BaseEntity;
import ru.kazenin.cashezavr.outside.jpa.ReceiptJpa;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadReceiptRouter {

    @Value("${cherry.executors.loadPoll}")
    private int pollSize;

    private final ReentrantLock runLock = new ReentrantLock();

    private final ReceiptJpa receiptJpa;
    private final LoadReceiptTask loadReceiptTask;

    @PostConstruct
    void postConstruct() {
        log.debug("Новый роутер с пулом исполнения {}", pollSize);
    }

    @RabbitListener(queues = {InfraNames.RECEIPT_LOAD_MESSAGES})
    private void trigger() throws InterruptedException {
        if (!runLock.tryLock() &&
                !runLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
            return;
        }

        try {
            route();
        } finally {
            runLock.unlock();
        }
    }

    private void route() {
        var receiptsUuids = receiptJpa.findAllByLoadedNullAndLoadLockFalse()
                .stream().map(BaseEntity::getUuid).toList();

        receiptsUuids.forEach(loadReceiptTask::load);
    }
}
