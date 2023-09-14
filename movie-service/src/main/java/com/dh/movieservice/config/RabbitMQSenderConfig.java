package com.dh.movieservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    @Value("${queue1.serie.name}")
    private String serieQueue;

    @Bean
    public Queue queue() { return new Queue(this.serieQueue, true); }

}
