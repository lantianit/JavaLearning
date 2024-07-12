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
@Order(1)
public class AspectDemo3 {
    @Before("com.bite.aop.aspect.AspectDemo.pt()")
    public void doBefore(){
        log.info("执行AspectDemo3.before方法...");
    }

    @After("com.bite.aop.aspect.AspectDemo.pt()")
    public void doAfter(){
        log.info("执行AspectDemo3.after方法...");
    }
}
