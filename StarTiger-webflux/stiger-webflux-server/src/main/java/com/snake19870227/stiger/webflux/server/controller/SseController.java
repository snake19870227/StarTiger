package com.snake19870227.stiger.webflux.server.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/sse")
public class SseController {

    @GetMapping(path = "/timer")
    public Flux<ServerSentEvent<Integer>> timer() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(seq.toString())
                        .data(ThreadLocalRandom.current().nextInt())
                        .build()
                );
    }
}
