package com.snake19870227.stiger.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
@EnableConfigServer
public class StarTigerGitBusConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerGitBusConfigServerApplication.class, args);
    }
}
