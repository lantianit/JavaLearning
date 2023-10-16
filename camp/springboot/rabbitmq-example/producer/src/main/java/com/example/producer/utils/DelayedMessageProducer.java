package com.example.producer.utils;

import com.example.producer.config.DelayedExchangeConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DelayedMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelayedMessage(String message) {
        System.out.println("sendDelayedMessage");
        rabbitTemplate.convertAndSend(DelayedExchangeConfig.EXCHANGE_NAME,
                DelayedExchangeConfig.ROUTING_KEY,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setDelay(10000); // 设置延迟时间，单位毫秒
                    return messagePostProcessor;
                });
    }
}
