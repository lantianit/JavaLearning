package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/art")
public class ArticleController {

    @RequestMapping("/hi")
    public String sayHi() {
        System.out.println("文章的 sayHI~");
        return "Hi, world.";
    }

}