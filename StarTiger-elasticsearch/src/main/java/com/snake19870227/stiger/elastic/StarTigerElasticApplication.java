package com.snake19870227.stiger.elastic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
public class StarTigerElasticApplication {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerElasticApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StarTigerElasticApplication.class, args);
        logger.info(context.getDisplayName());
    }
}
