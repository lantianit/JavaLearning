package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutMessageConsumer2 {

    @RabbitListener(queues = "queue2")
    public void handleMessage(String message) {
        System.out.println("Consumer 2 - Received message: " + message);
        // 在这里处理消息逻辑
    }
}