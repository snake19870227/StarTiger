package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bu HuaYang
 */
@FeignClient(name = "producer-eureka", fallback = HelloServiceFallback.class)
public interface HelloService {

    @GetMapping(path = "/hello")
    MapRestResponse hello(@RequestParam(name = "somebody") String somebody);
}
