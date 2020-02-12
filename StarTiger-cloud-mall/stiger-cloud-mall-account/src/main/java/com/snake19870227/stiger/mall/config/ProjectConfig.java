package com.snake19870227.stiger.mall.config;

import com.snake19870227.stiger.mall.message.BusMessageHandlerScan;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang
 */
@Configuration
@BusMessageHandlerScan("com.snake19870227.stiger.mall.message.handler")
public class ProjectConfig {
}
