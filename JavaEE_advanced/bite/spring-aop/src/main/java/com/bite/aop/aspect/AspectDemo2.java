package com.bite.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@Aspect
@Order(4)
public class AspectDemo2 {
    @Before("com.bite.aop.aspect.AspectDemo.pt()")
    public void doBefore(){
        log.info("执行AspectDemo2.before方法...");
    }

    @After("com.bite.aop.aspect.AspectDemo.pt()")
    public void doAfter(){
        log.info("执行AspectDemo2.after方法...");
    }
}
