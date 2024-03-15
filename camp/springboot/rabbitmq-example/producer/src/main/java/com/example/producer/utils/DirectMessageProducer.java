package com.example.producer.utils;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "routingKey", message);
        System.out.println("Message sent to Direct Exchange: " + message);
    }
}
