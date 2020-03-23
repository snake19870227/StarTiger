package com.snake19870227.stiger.qrcode.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.web.exception.GlobalHandlerExceptionResolver;
import com.snake19870227.stiger.web.exception.PostWebErrorHandler;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/13
 */
@Configuration
public class ProjectConfig {

    @Bean
    public GlobalHandlerExceptionResolver globalHandlerExceptionResolver(ObjectProvider<PostWebErrorHandler> postExceptionHandlerProvider) {
        return new GlobalHandlerExceptionResolver(postExceptionHandlerProvider);
    }
}
