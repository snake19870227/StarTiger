package com.snake19870227.stiger.cloud.consumer.remote;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Bu HuaYang
 */
@FeignClient(name = "producer-eureka", fallback = HelloServiceFallback.class)
public interface HelloServiceHystrix extends HelloService {
}
