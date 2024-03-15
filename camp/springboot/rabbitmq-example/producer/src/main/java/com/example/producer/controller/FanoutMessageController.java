package com.example.producer.controller;

import com.example.producer.utils.FanoutMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fanout")
public class FanoutMessageController {

    @Autowired
    private FanoutMessageProducer fanoutMessageProducer;

    @GetMapping("/send")
    public String sendFanoutMessage(@RequestParam String message) {
        fanoutMessageProducer.sendMessage(message);
        return "Message sent to Fanout Exchange: " + message;
    }
}
