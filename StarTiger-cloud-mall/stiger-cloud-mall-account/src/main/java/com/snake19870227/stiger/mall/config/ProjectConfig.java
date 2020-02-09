package com.snake19870227.stiger.mall.config;

import com.snake19870227.stiger.mall.message.BusMessageHandlerScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang
 */
@Configuration
@BusMessageHandlerScan("com.snake19870227.stiger.mall.message.handler")
public class ProjectConfig {
}
