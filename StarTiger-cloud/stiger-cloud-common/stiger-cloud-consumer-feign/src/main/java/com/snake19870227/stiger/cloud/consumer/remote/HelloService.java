package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bu HuaYang
 */
@FeignClient(name = "producer-eureka")
public interface HelloService {

    @GetMapping(path = "/hello")
    KeyValueRestResponse hello(@RequestParam(name = "somebody") String somebody);
}
