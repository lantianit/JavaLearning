package com.demo.component;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleController {
    public String sayHello() {
        return "Hello,Controler.";
    }
}
