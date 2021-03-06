package com.snake19870227.stiger.cloud.producer.controller;

import com.snake19870227.stiger.cloud.producer.service.HelloService;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.web.restful.RestResponse;
import com.snake19870227.stiger.web.restful.RestResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class HelloController {

    @Value("${stiger.env:noenv}")
    private String env;

    @Value("${stiger.env2:noenv2}")
    private String env2;

    @Autowired
    private HelloService helloService;

    @GetMapping(path = "/hello")
    public RestResponse.DefaultRestResponse hello(@RequestParam(name = "somebody", defaultValue = "nobody") String somebody) {
        Map<String, String> dataMap = new HashMap<>(3);
        dataMap.put("title", helloService.sayHello(somebody));
        dataMap.put("env", env);
        dataMap.put("env2", env2);
        dataMap.put("profiles", Arrays.toString(StarTigerContext.getActiveProfiles()));
        return RestResponseBuilder.createSuccessDefaultRestResp(dataMap);
    }
}
