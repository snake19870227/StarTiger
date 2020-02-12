package com.snake19870227.stiger.webflux.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Bu HuaYang
 */
@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public Mono<String> hello() {
        return Mono.just("Hello Spring WebFlux");
    }
}
