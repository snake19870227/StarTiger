package com.snake19870227.stiger.admin.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Bu HuaYang
 */
@Configuration
@MapperScan(basePackages = {
        "com.snake19870227.stiger.admin.web.dao.mapper"
})
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfig {
}
