package com.example.producer.utils;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    public void sendMessage(String message, String routing) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), routing, message);
        System.out.println("Message sent to Topic Exchange with routing key " + routing + ": " + message);
    }
}
