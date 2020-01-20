package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bu HuaYang
 */
@Profile("feign")
@FeignClient(name = "producer-eureka", fallback = HelloServiceHystrix.class)
public interface HelloService {

    @GetMapping(path = "/hello")
    KeyValueRestResponse hello();
}
