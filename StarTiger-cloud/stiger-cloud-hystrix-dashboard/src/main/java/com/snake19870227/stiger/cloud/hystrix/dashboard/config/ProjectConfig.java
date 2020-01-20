package com.snake19870227.stiger.cloud.hystrix.dashboard.config;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableHystrixDashboard
public class ProjectConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("base-messages");
        return messageSource;
    }
}
