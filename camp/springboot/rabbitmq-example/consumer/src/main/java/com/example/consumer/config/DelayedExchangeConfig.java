package com.example.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 延迟交换器和队列
 */
@Configuration
public class DelayedExchangeConfig {
    public static final String EXCHANGE_NAME = "myDelayedExchange";
    public static final String QUEUE_NAME = "delayed.queue";
    public static final String ROUTING_KEY = "delayed.routing.key";

    @Bean
    public CustomExchange delayedExchange() {
        return new CustomExchange(EXCHANGE_NAME,
                "x-delayed-message",
                true,
                false);
    }

    @Bean
    public Queue delayedQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .withArgument("x-delayed-type", "direct")
                .build();
    }

    @Bean
    public Binding delayedBinding() {
        return BindingBuilder.bind(delayedQueue()).to(delayedExchange()).with(ROUTING_KEY).noargs();
    }
}
