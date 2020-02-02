package com.snake19870227.stiger.cloud.base.config;

import com.snake19870227.stiger.cloud.base.properties.StarTigerCloudProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableConfigurationProperties(StarTigerCloudProperties.class)
public class BaseConfig {
}
