package com.bite.aop.controller;

import com.bite.aop.aspect.TimeRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/u1")
    public String u1(){
        log.info("执行u1方法");
        return "u1";
    }

    @TimeRecord
    @RequestMapping("/u2")
    public String u2(){
        log.info("执行u2方法");
        return "u2";
    }

}
