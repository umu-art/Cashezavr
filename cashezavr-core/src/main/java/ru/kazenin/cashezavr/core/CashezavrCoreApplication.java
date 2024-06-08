package ru.kazenin.cashezavr.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ru.kazenin.cashezavr.common.entity")
public class CashezavrCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashezavrCoreApplication.class, args);
    }

}
