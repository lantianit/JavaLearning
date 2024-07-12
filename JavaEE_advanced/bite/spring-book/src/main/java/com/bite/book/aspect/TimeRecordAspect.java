package com.bite.book.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimeRecordAspect {

    @Around("execution(* com.bite.book.controller.*.*(..))")
    public Object record(ProceedingJoinPoint pj) throws Throwable {
        /**
         * 切面逻辑
         * 1. 记录开始时间
         * 2. 执行目标方法
         * 3. 记录结束时间
         * 4. 记录消耗时间
         */
        long start = System.currentTimeMillis();
        Object result = pj.proceed();
        pj.proceed();
        log.info(pj.getSignature()+"cost time:"+(System.currentTimeMillis()-start)+"ms");
        log.info(pj.getSignature().toString());
        log.info(pj.toLongString());
        log.info(pj.toShortString());
        log.info(String.valueOf(pj.getArgs()));
        return result;
    }
}
