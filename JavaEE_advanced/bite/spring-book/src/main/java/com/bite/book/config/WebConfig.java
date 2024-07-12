package com.bite.book.config;

import com.bite.book.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private List<String> excludePath = Arrays.asList(
            "/user/login",
            "/**/login.html",
            "/css/**",
            "/js/**",
            "/pic/**"
    );
    @Autowired
    private LoginInterceptor loginInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // /** 表示对所有的路径生效
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
//                //排除一些路径
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/**/**.html")
//                .excludePathPatterns("/css/**")
//                .excludePathPatterns("/js/**")
//                .excludePathPatterns("/pic/**");
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /** 表示对所有的路径生效
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
    }
}
