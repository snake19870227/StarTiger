package com.snake19870227.stiger.webflux.server.config;

import com.snake19870227.stiger.webflux.server.service.CarService;
import com.snake19870227.stiger.webflux.server.service.impl.MemoryCarServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang
 */
@Configuration
public class ProjectConfig {

    @Bean
    @ConditionalOnMissingBean(CarService.class)
    public CarService carService() {
        return new MemoryCarServiceImpl();
    }
}
