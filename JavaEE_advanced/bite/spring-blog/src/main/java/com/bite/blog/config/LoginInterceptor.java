package com.bite.blog.config;

import com.bite.blog.constants.Constant;
import com.bite.blog.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 从header中获取token
        //2. 校验token
        //3. 成功, 放行
        String userToken = request.getHeader(Constant.USER_TOKEN_HEADER);
        log.info("获得token, token:"+userToken);
        boolean result = JwtUtils.checkToken(userToken);
        if (result){
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
