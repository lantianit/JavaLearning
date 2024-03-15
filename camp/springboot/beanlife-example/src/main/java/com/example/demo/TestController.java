package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/say")
    public String getSay(){
        return "get say";
    }

    @PostMapping("/say")
    public String postSay(){
        return "post say";
    }

}
