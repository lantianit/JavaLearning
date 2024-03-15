package com.example.producer.controller;

import com.example.producer.utils.DirectMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direct")
public class DirectMessageController {

    @Autowired
    private DirectMessageProducer directMessageProducer;

    @GetMapping("/send")
    public String sendDirectMessage(@RequestParam String message) {
        directMessageProducer.sendMessage(message);
        return "Message sent to Direct Exchange: " + message;
    }
}
