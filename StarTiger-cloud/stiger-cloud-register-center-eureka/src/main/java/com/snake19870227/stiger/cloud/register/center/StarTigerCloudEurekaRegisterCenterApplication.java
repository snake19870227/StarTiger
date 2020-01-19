package com.snake19870227.stiger.cloud.register.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
@EnableEurekaServer
public class StarTigerCloudEurekaRegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerCloudEurekaRegisterCenterApplication.class, args);
    }
}
