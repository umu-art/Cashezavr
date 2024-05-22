package ru.kazenin.cherry.outside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ru.kazenin.cherry.common.entity")
public class CherryOutsideApplication {

    public static void main(String[] args) {
        SpringApplication.run(CherryOutsideApplication.class, args);
    }

}
