package com.snake19870227.stiger.cloud.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
public class StarTigerCloudEurekaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerCloudEurekaProducerApplication.class, args);
    }
}
