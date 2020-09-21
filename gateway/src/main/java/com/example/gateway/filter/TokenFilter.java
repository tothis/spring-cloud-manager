package com.example.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import com.example.gateway.constant.GlobalFilterOrderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * token过滤器
 *
 * @author 李磊
 * @since 1.0
 */
@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        List<String> tokenList = headers.get("token");
        if (CollUtil.isNotEmpty(tokenList)) {
            String token = String.valueOf(tokenList);
            log.info("token -> [{}]", token);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return GlobalFilterOrderConstant.TOKEN;
    }
}