package com.snake19870227.stiger.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Bu HuaYang
 */
@SpringBootApplication
@EnableZuulProxy
public class StarTigerCloudGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerCloudGatewayZuulApplication.class, args);
    }
}
