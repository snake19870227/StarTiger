package com.snake19870227.stiger.cloud.gateway.filter;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Bu HuaYang
 */
public abstract class VisitorFilter implements GatewayFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(VisitorFilter.class);

    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> map = request.getQueryParams();
        String somebody = map.getFirst("somebody");
        String uri = request.getURI().toString();
        if (StrUtil.isNotBlank(somebody)) {
            Integer times = getVisitTimes(somebody);
            logger.info("{} 第 {} 次访问 {}", somebody, times, uri);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    protected abstract Integer getVisitTimes(String visitor);
}
