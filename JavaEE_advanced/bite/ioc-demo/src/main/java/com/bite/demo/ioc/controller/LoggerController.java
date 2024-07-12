package com.bite.demo.ioc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@RestController
public class LoggerController {

    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @RequestMapping("/printLog")
    public String printLog(){
        System.out.println("打印日志");
        logger.info("======我是日志对象打印的日志=====");
        return "success";
    }
    @RequestMapping("/logLevel")
    public String logLevel(){
        logger.trace("我是trace级别的日志");
        logger.debug("我是debug级别的日志");
        logger.info("我是info级别的日志");
        logger.warn("我是warn级别的日志");
        logger.error("我是error级别的日志");
        return "success";
    }
}
