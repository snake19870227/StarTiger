package com.snake19870227.stiger.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
@EnableScheduling
public class StarTigerCloudEurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerCloudEurekaConsumerApplication.class, args);
    }
}
