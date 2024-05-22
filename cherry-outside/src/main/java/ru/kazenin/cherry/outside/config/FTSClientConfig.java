package ru.kazenin.cherry.outside.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class FTSClientConfig {

    /**
     * Клиент для общения с ФНС. Конфигурировать через props
     *
     * @param host          хост ФНС
     * @param userAgent     подменный User-Agent
     * @param deviceOS      подменный Device-OS
     * @param deviceID      подмменный Device-Id
     * @param clientVersion подменный clientVersion
     * @return бин - rest client
     */
    @Bean("ftsClient")
    public RestClient ftsClient(
            @Value("${fns.host}") String host,
            @Value("${fns.userAgent}") String userAgent,
            @Value("${fns.deviceOS}") String deviceOS,
            @Value("${fns.deviceID}") String deviceID,
            @Value("${fns.clientVersion}") String clientVersion) {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl(host)
                .defaultHeader("Host", host)
                .defaultHeader("Accept", "*/*")
                .defaultHeader("Accept-Language", "ru-RU;q=1, en-US;q=0.9")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("User-Agent", userAgent)
                .defaultHeader("Device-OS", deviceOS)
                .defaultHeader("Device-Id", deviceID)
                .defaultHeader("clientVersion", clientVersion)
                .build();

    }
}