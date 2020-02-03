package com.snake19870227.stiger.cloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
@EnableAdminServer
public class StarTigerCloudAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerCloudAdminServerApplication.class, args);
    }
}
