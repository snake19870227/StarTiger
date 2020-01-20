package com.snake19870227.stiger.cloud.consumer.config;

import com.snake19870227.stiger.cloud.consumer.remote.HelloServiceHystrix;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableFeignClients(basePackages = "com.snake19870227.stiger.cloud.consumer.remote")
public class ProjectConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("base-messages");
        return messageSource;
    }

    @Bean
    public HelloServiceHystrix helloServiceHystrix() {
        return new HelloServiceHystrix();
    }
}
