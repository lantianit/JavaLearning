package com.example.consumer;

import com.example.consumer.config.DelayedExchangeConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class DelayedMessageConsumer {

    @RabbitListener(queues = DelayedExchangeConfig.QUEUE_NAME)
    public void receiveDelayedMessage(String message) {
        System.out.println("Received delayed message: " + message);
    }
}
