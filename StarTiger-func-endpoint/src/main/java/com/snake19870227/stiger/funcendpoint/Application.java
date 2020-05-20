package com.snake19870227.stiger.funcendpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/05/20
 */
@SpringBootApplication
@Configuration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> fooFunction() {
        return RouterFunctions.route()
                .GET("/hello", request -> ServerResponse.ok().body("snake"))
                .build();
    }
}
