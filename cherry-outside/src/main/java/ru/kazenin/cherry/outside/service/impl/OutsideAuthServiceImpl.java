package ru.kazenin.cherry.outside.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.kazenin.cherry.outside.service.OutsideAuthService;

import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutsideAuthServiceImpl implements OutsideAuthService {

    private final RestClient restClient;

    @Value("${fns.clientSecret}")
    private String clientSecret;

    @Value("${fns.deviceOS}")
    String deviceOS;

    private String phone;
    private String sessionId;
    private String refreshToken;

    @Override
    public void sendPhone(String phone) {
        log.info("Отправка запроса {}", phone);
        this.phone = phone;

        var response = restClient.post()
                .uri("/v2/auth/phone/request")
                .body("{\"phone\": \"" + phone + "\", " +
                        "\"client_secret\": \"" + clientSecret + "\", " +
                        "\"os\": \"" + deviceOS + "\"}")
                .retrieve()
                .toBodilessEntity();

        log.info("Начало авторизации, код ответа {}", response.getStatusCode());
    }

    @Override
    public void sendCode(String code) {
        log.info("Отправка кода {}", code);
        var result = restClient.post()
                .uri("/v2/auth/phone/verify")
                .body("{\"phone\": \"" + phone + "\", " +
                        "\"client_secret\": \"" + clientSecret + "\", " +
                        "\"code\": \"" + code + "\", " +
                        "\"os\": \"" + deviceOS + "\"}\n")
                .retrieve()
                .body(JsonNode.class);

        if (isNull(result)) {
            throw new RuntimeException("Ошибка авторизации");
        }

        sessionId = result.get("sessionId").asText();
        refreshToken = result.get("refreshToken").asText();

        log.info("Успешная авторизация");
    }

    @Override
    public String getSessionId() {
        if (isNull(sessionId)) {
            throw new RuntimeException("Нет сессии");
        }

        return sessionId;
    }
}
