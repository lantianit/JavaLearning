package com.example.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 此方法返回 true 则执行下面 beforeBodyWrite 方法
     * 反之则不执行
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "");
        result.put("data", body);
        if (body instanceof String) {
            // 需要特殊处理，因为 String 在转换的时候会报错
            try {
                return objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}