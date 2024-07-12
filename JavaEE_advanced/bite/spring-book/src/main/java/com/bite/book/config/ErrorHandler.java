package com.bite.book.config;

import com.bite.book.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ErrorHandler {
    /**
     * 捕获异常, 返回统一的结果
     */
    @ExceptionHandler
    public Result handler(Exception e){
        log.error("捕获异常, e:", e);
        return Result.fail("内部发生错误"+e.getMessage());
    }

    @ExceptionHandler
    public Result handler(NullPointerException e){
        log.error("捕获异常, e:", e);
        return Result.fail("内部发生NullPointerException错误");
    }

    @ExceptionHandler
    public Result handler(RuntimeException e){
        log.error("捕获异常, e:", e);
        return Result.fail("内部发生RuntimeException错误");
    }

    @ExceptionHandler
    public Result handler(ArithmeticException e){
        log.error("捕获异常, e:", e);
        return Result.fail("内部发生ArithmeticException错误");
    }
}
