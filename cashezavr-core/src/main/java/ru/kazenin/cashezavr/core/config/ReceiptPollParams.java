package ru.kazenin.cashezavr.core.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ReceiptPollParams {

    @Value("${cashezavr.receipts.poll.maxTime}")
    private int pollMaxTime;

    @Value("${cashezavr.receipts.poll.waitTime}")
    private int pollWaitTime;
}
