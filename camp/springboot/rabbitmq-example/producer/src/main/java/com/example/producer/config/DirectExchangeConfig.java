package com.example.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("myDirectExchange");
    }

    @Bean
    public Queue directQueue() {
        return new Queue("directQueue");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("routingKey");
    }
}