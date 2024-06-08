package ru.kazenin.cashezavr.outside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("ru.kazenin.cashezavr.common.entity")
public class CashezavrOutsideApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashezavrOutsideApplication.class, args);
    }

}
