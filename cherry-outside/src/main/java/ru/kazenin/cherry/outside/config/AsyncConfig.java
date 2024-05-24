package ru.kazenin.cherry.outside.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "loadExecutor")
    public Executor loadExecutor(@Value("${cherry.executors.loadPoll}") int pollSize) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(pollSize);
        executor.setMaxPoolSize(pollSize);
        executor.initialize();
        return executor;
    }
}
