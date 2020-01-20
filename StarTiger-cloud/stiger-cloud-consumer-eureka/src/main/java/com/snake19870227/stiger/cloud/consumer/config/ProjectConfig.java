package com.snake19870227.stiger.cloud.consumer.config;

import com.snake19870227.stiger.http.RestResponse;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableCircuitBreaker
public class ProjectConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("base-messages");
        return messageSource;
    }

    @Profile("balancer")
    @Configuration
    public static class BalancerModeConfig {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @Profile("ribbon")
    @Configuration
    public static class RibbonModeConfig {

        @LoadBalanced
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @Profile("feign")
    @Configuration
    @EnableFeignClients(basePackages = "com.snake19870227.stiger.cloud.consumer.remote")
    public static class FeignModeConfig {
    }
}
