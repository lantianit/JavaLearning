package com.example.sentinel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        System.out.println("Do test()");
        return "Hello, test.";
    }

    @RequestMapping("/delay")
    public String delay() throws InterruptedException {
        Thread.sleep(1200);
        System.out.println("Do delay()");
        return "Hello, delay.";
    }

}
