package com.snake19870227.stiger.qrcode.config;

import com.snake19870227.stiger.exception.GlobalHandlerExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/13
 */
@Configuration
public class ProjectConfig {

    @Bean
    public GlobalHandlerExceptionResolver globalHandlerExceptionResolver() {
        return new GlobalHandlerExceptionResolver();
    }
}
