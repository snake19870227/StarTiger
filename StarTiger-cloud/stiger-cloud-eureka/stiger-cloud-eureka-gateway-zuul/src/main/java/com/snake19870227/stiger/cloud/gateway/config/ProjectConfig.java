package com.snake19870227.stiger.cloud.gateway.config;

import com.snake19870227.stiger.cloud.gateway.filter.MemoryVisitorFilter;
import com.snake19870227.stiger.cloud.gateway.filter.VisitorFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang
 */
@Configuration
public class ProjectConfig {

    @Bean
    @ConditionalOnMissingBean(VisitorFilter.class)
    public VisitorFilter visitorFilter() {
        return new MemoryVisitorFilter();
    }
}
