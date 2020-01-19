package com.snake19870227.stiger.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bu HuaYang
 */
@Configuration
public class StarTigerAutoConfiguration {

    @Bean
    public StarTigerContextLoader starTigerContextLoader() {
        return new StarTigerContextLoader();
    }
}
