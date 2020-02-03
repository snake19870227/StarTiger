package com.snake19870227.stiger.cloud.gateway.config;

import com.snake19870227.stiger.cloud.gateway.filter.MemoryVisitorFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang
 */
@Configuration
public class ProjectConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                      .route(predicateSpec -> predicateSpec.path("/producer-eureka/**")
                              .filters(gatewayFilterSpec -> gatewayFilterSpec
                                      .stripPrefix(1)
                                      .filter(new MemoryVisitorFilter())
                                      .addResponseHeader("Gateway-By", "Spring Cloud Gateway"))
                              .uri("lb://PRODUCER-EUREKA")
                              .order(0)
                              .id("customer_ProducerEureka"))
                      .build();
        // @formatter:on
    }
}
