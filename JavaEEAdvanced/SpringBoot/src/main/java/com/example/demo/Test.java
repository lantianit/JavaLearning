package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baidu")
class Test {
    @RequestMapping("/good")
    public String test() {
        return "你好 百度";
    }
}