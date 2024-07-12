package com.bite.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AspectDemo {
    @Pointcut("execution(* com.bite.aop..*(..))")
    public void pt(){};

    @Before("pt()")
    public void doBefore(){
        log.info("执行AspectDemo.before方法...");
    }

//    @After("pt()")
//    public void doAfter(){
//        log.info("执行AspectDemo.after方法...");
//    }
//
//    @AfterReturning("execution(* com.bite.aop.controller.*.*(..))")
//    public void doAfterReturn(){
//        log.info("执行AspectDemo.doAfterReturn方法...");
//    }
//
//    @AfterThrowing("execution(* com.bite.aop.controller.*.*(..))")
//    public void doAfterThrow(){
//        log.info("执行AspectDemo.doAfterThrow方法...");
//    }
//
//    @Around("execution(* com.bite.aop.controller.*.*(..))")
//    public Object doAround(ProceedingJoinPoint joinPoint) {
//        log.info("执行AspectDemo.doAround 目标方法前...");
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//        }catch (Throwable e) {
//            log.error(joinPoint.toShortString()+"发生异常, e:",e);
//        }
//        log.info("执行AspectDemo.doAround 目标方法后...");
//        return result;
//    }
}
