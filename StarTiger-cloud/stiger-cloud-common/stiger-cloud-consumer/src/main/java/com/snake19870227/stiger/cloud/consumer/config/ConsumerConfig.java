package com.snake19870227.stiger.cloud.consumer.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableCircuitBreaker
public class ConsumerConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(1L))
                .setReadTimeout(Duration.ofSeconds(5L))
                .build();
    }
}
