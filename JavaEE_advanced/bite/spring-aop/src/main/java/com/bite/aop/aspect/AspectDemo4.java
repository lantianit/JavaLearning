package com.bite.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@Aspect
@Order(3)
public class AspectDemo4 {
    @Before("com.bite.aop.aspect.AspectDemo.pt()")
    public void doBefore(){
        log.info("执行AspectDemo4.before方法...");
    }

    @After("com.bite.aop.aspect.AspectDemo.pt()")
    public void doAfter(){
        log.info("执行AspectDemo4.after方法...");
    }
}
