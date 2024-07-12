package com.bite.blog.config;

import com.bite.blog.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    public Result handler(Exception e){
        log.error("发生异常, e:", e);
        return Result.fail(e.getMessage());
    }
}
