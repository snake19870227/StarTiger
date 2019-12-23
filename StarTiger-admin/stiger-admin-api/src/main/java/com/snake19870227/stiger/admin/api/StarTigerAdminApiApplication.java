package com.snake19870227.stiger.admin.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.snake19870227.stiger.admin"
        }
)
public class StarTigerAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerAdminApiApplication.class, args);
    }
}
