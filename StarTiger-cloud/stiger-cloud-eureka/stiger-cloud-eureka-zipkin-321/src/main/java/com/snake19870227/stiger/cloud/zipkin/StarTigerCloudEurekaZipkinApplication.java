package com.snake19870227.stiger.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.snake19870227.stiger.cloud.base",
                "com.snake19870227.stiger.cloud.zipkin",
                "com.snake19870227.stiger.cloud.producer"
        }
)
public class StarTigerCloudEurekaZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerCloudEurekaZipkinApplication.class, args);
    }
}
