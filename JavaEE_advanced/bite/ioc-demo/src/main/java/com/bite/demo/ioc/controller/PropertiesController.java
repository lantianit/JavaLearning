package com.bite.demo.ioc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/prop")
@RestController
public class PropertiesController {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesController.class);

    @Value("bite.key1")
    private String key1;

    @Value("${bite.key2}")
    private Integer key2;

    @RequestMapping("/readValue")
    public String readValue(){

        return "从Properties读取配置文件, key1:"+key1+",key2:"+key2;
    }
}
