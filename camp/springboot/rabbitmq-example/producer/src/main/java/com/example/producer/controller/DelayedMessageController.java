package com.example.producer.controller;

import com.example.producer.utils.DelayedMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delayed")
public class DelayedMessageController {
    @Autowired
    private DelayedMessageProducer delayedMessageProducer;

    @GetMapping("/send")
    public String sendDirectMessage(@RequestParam String message) {
        delayedMessageProducer.sendDelayedMessage(message);
        return "Delayed message sent to Exchange: " + message;
    }
}
