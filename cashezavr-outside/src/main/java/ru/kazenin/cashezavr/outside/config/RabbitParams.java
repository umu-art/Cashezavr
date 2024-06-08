package ru.kazenin.cashezavr.outside.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RabbitParams {

    @Value("${rabbit.host}")
    private String host;

    @Value("${rabbit.login}")
    private String login;

    @Value("${rabbit.password}")
    private String password;
}
