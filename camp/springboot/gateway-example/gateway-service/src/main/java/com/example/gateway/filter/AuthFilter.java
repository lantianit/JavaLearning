package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 得到 request、response 对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 业务逻辑代码
        if(request.getQueryParams().getFirst("auth")==null){
            // 权限有问题返回，并结束执行
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        // 此步骤正常，执行下一步
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 此值越小越早执行
        return 1;
    }
}
