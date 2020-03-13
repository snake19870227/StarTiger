package com.snake19870227.stiger.admin.web.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.web.exception.GlobalHandlerExceptionResolver;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableCaching
public class ProjectConfig {

    @Bean
    public GlobalHandlerExceptionResolver exceptionResolver() {
        return new GlobalHandlerExceptionResolver();
    }
}
