package com.snake19870227.stiger.cloud.producer.controller;

import com.snake19870227.stiger.cloud.producer.service.HelloService;
import com.snake19870227.stiger.context.StarTigerContext;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(path = "/hello")
    public RestResponse.DefaultRestResponse hello(@RequestParam(name = "somebody", defaultValue = "nobody") String somebody) {
        Map<String, String> dataMap = new HashMap<>(2);
        dataMap.put("title", helloService.sayHello(somebody));
        dataMap.put("content", "Profiles " + Arrays.toString(StarTigerContext.getActiveProfiles()));
        return RestResponseBuilder.createSuccessRestResp(dataMap);
    }
}
