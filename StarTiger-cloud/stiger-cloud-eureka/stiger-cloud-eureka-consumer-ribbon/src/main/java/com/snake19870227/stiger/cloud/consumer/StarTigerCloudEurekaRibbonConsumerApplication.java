package com.snake19870227.stiger.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.snake19870227.stiger.cloud.base",
                "com.snake19870227.stiger.cloud.consumer"
        }
)
@EnableScheduling
public class StarTigerCloudEurekaRibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerCloudEurekaRibbonConsumerApplication.class, args);
    }
}
