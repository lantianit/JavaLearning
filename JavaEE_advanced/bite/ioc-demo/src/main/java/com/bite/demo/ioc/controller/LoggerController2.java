package com.bite.demo.ioc.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log2")
@RestController
public class LoggerController2 {

    @RequestMapping("/logLevel")
    public String logLevel(){
        log.trace("我是trace级别的日志");
        log.debug("我是debug级别的日志");
        log.info("我是info级别的日志");
        log.warn("我是warn级别的日志");
        log.error("我是error级别的日志");
        return "success";
    }
}
