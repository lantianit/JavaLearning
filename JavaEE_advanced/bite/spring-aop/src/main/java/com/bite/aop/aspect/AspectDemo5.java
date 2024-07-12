package com.bite.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@Aspect
public class AspectDemo5 {
    @Around("@annotation(com.bite.aop.aspect.TimeRecord)")
    public Object timeRecord(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (Throwable e){
            log.error(joinPoint.toString()+"发生异常, e:",e);
        }
        log.info(joinPoint.toString()+"执行时间: "+ (System.currentTimeMillis()-start) + "ms");
        return result;
    }
}
