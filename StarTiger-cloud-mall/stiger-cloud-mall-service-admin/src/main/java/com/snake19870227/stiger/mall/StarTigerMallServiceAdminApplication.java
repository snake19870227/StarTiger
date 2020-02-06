package com.snake19870227.stiger.mall;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
@EnableAdminServer
public class StarTigerMallServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerMallServiceAdminApplication.class, args);
    }
}
