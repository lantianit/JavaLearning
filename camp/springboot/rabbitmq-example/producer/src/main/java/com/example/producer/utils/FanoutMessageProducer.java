package com.example.producer.utils;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
        System.out.println("Message sent to Fanout Exchange: " + message);
    }
}
