package ru.kazenin.cherry.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ru.kazenin.cherry.common.entity")
public class CherryCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CherryCoreApplication.class, args);
    }

}
