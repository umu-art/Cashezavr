package ru.kazenin.cherry.core.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kazenin.cherry.common.dto.InfraNames;

@Configuration
@EnableRabbit
public class RabbitConfig {
    @Bean
    public ConnectionFactory connectionFactory(RabbitParams rabbitParams) {
        var connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitParams.getHost());
        connectionFactory.setUsername(rabbitParams.getLogin());
        connectionFactory.setPassword(rabbitParams.getPassword());
        connectionFactory.setConnectionTimeout(100000);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue receiptUploaded() {
        return new Queue(InfraNames.RECEIPT_LOAD_MESSAGES);
    }
}
