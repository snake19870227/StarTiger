package com.snake19870227.stiger.admin.web.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.admin.web.common.Error4xxViewResolver;
import com.snake19870227.stiger.admin.web.common.WebPostWebErrorHandler;
import com.snake19870227.stiger.web.exception.GlobalHandlerExceptionResolver;
import com.snake19870227.stiger.web.exception.PostWebErrorHandler;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableCaching
public class ProjectConfig {

    @Bean
    public WebPostWebErrorHandler postWebErrorHandler() {
        return new WebPostWebErrorHandler();
    }

    @Bean
    public GlobalHandlerExceptionResolver exceptionResolver(ObjectProvider<PostWebErrorHandler> postWebErrorHandlers) {
        return new GlobalHandlerExceptionResolver(postWebErrorHandlers);
    }

    @Bean
    public Error4xxViewResolver errorViewResolver(ObjectProvider<PostWebErrorHandler> postWebErrorHandlers) {
        return new Error4xxViewResolver(postWebErrorHandlers);
    }
}
