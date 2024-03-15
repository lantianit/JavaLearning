package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectMessageConsumer2 {

    @RabbitListener(queues = "directQueue")
    public void handleMessage(String message) {
        System.out.println("Consumer 2 - Received message: " + message);
        // 在这里处理消息逻辑
    }
}
