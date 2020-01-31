package com.snake19870227.stiger.cloud.consumer.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableFeignClients(basePackages = "com.snake19870227.stiger.cloud.consumer.remote")
public class ProjectConfig {
}
