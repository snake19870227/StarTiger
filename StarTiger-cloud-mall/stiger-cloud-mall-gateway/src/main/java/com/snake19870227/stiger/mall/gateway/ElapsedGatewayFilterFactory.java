package com.snake19870227.stiger.mall.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bu HuaYang
 */
public class ElapsedGatewayFilterFactory extends AbstractGatewayFilterFactory<ElapsedGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(ElapsedGatewayFilterFactory.class);

    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    public ElapsedGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.stream(Config.class.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
                        if (startTime != null) {
                            StringBuilder patternStr = new StringBuilder("{}: {}ms");
                            if (config.isWithParams()) {
                                logger.info(patternStr.append("; params: {}").toString(),
                                        exchange.getRequest().getURI().getRawPath(),
                                        (System.currentTimeMillis() - startTime),
                                        exchange.getRequest().getQueryParams());
                            } else {
                                logger.info(patternStr.toString(),
                                        exchange.getRequest().getURI().getRawPath(),
                                        (System.currentTimeMillis() - startTime));
                            }

                        }
                    })
            );
        };
    }

    public static class Config {

        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }

    }
}
