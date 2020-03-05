package com.snake19870227.stiger.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableScheduling
@MapperScan(basePackages = {
        "com.snake19870227.stiger.mall.mapper"
})
@EnableTransactionManagement(proxyTargetClass = true)
public class ProjectConfig {
}
