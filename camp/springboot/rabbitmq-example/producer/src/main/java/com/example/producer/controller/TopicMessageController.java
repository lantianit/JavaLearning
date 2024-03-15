package com.example.producer.controller;

import com.example.producer.utils.TopicMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicMessageController {

    @Autowired
    private TopicMessageProducer topicMessageProducer;

    @GetMapping("/send")
    public String sendTopicMessage1(@RequestParam String message) {
        topicMessageProducer.sendMessage(message, "topic.routing.key1");
        return "Message sent to Topic Exchange with routing key topic.routing.key1: " + message;
    }

    @GetMapping("/send2")
    public String sendTopicMessage2(@RequestParam String message) {
        topicMessageProducer.sendMessage(message, "topic.key2");
        return "Message sent to Topic Exchange with routing key topic.routing.key2: " + message;
    }
}
