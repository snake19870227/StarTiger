package com.snake19870227.stiger.cloud.consumer.config;

import com.snake19870227.stiger.cloud.consumer.remote.HelloServiceFallback;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableCircuitBreaker
@EnableFeignClients(basePackages = "com.snake19870227.stiger.cloud.consumer.remote")
public class ProjectConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("base-messages");
        return messageSource;
    }

    @Bean
    public HelloServiceFallback helloServiceHystrix() {
        return new HelloServiceFallback();
    }
}
