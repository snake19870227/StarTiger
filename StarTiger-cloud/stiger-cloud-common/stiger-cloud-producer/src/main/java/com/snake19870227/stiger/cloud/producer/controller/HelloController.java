package com.snake19870227.stiger.cloud.producer.controller;

import com.snake19870227.stiger.context.StarTigerContext;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public RestResponse.DefaultRestResponse hello() {
        Map<String, String> dataMap = new HashMap<>(2);
        dataMap.put("title", "Hello StarTiger Cloud.");
        dataMap.put("content", "Profiles " + Arrays.toString(StarTigerContext.getActiveProfiles()));
        return RestResponseBuilder.createSuccessRestResp(dataMap);
    }
}
