package com.snake19870227.stiger.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/15
 */
@SpringBootApplication
public class StarTigerOauth2ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerOauth2ResourceApplication.class, args);
    }

    @Configuration
    @EnableResourceServer
    public static class Config {


    }

    @RestController
    public static class ResourceController {

        private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

        @GetMapping(path = "/hello")
        public String hello() {
            return "hello";
        }
    }
}
