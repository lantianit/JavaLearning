package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageConsumer {

    @RabbitListener(queues = "topicQueue1")
    public void handleMessage1(String message) {
        System.out.println("Consumer 1 - Received message: " + message);
        // 在这里处理消息逻辑
    }

    @RabbitListener(queues = "topicQueue2")
    public void handleMessage2(String message) {
        System.out.println("Consumer 2 - Received message: " + message);
        // 在这里处理消息逻辑
    }
}
