package ru.kazenin.cherry.core.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ReceiptPollParams {

    @Value("${cherry.receipts.poll.maxTime}")
    private int pollMaxTime;

    @Value("${cherry.receipts.poll.waitTime}")
    private int pollWaitTime;
}
