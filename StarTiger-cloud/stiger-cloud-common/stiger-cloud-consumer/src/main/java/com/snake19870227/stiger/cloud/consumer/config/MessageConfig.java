package com.snake19870227.stiger.cloud.consumer.config;

import com.snake19870227.stiger.cloud.consumer.properties.StarTigerCloudProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableConfigurationProperties(StarTigerCloudProperties.class)
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("base-messages", "consumer-messages");
        return messageSource;
    }
}
