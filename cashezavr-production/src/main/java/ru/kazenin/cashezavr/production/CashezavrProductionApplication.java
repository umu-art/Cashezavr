package ru.kazenin.cashezavr.production;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ru.kazenin.cashezavr.common.entity")
public class CashezavrProductionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashezavrProductionApplication.class, args);
    }

}
