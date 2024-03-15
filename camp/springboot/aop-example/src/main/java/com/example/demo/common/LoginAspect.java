package com.example.demo.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect  // 切面
@Component // 不能省略
public class LoginAspect {

    // 前置通知
    @Before("execution(* com.example.demo.service.impl.UserServiceImpl.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("前置通知："+
                joinPoint.getSignature().getName());
    }

}