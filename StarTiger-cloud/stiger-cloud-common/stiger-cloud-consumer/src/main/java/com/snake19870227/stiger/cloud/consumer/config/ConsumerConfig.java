package com.snake19870227.stiger.cloud.consumer.config;

import com.snake19870227.stiger.cloud.base.properties.StarTigerCloudProperties;
import com.snake19870227.stiger.cloud.base.service.HelloService;
import com.snake19870227.stiger.cloud.consumer.service.HelloServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author Bu HuaYang
 */
@Configuration
public class ConsumerConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(1L))
                .setReadTimeout(Duration.ofSeconds(5L))
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService(StarTigerCloudProperties starTigerCloudProperties, RestTemplate restTemplate) {
        return new HelloServiceImpl(starTigerCloudProperties, restTemplate);
    }
}
