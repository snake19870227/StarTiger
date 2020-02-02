package com.snake19870227.stiger.cloud.producer.controller;

import com.snake19870227.stiger.cloud.base.entity.dto.ListRestResponse;
import com.snake19870227.stiger.cloud.base.service.TraceAccess;
import com.snake19870227.stiger.context.StarTigerContext;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ProducerController {

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Value("${stiger.env:noenv}")
    private String env;

    @Autowired(required = false)
    private TraceAccess traceAccess;

    @GetMapping(path = "/access")
    public RestResponse.DefaultRestResponse access(@RequestParam(name = "visitor") String visitor) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("master", StarTigerContext.getApplicationName() + " [" + env + "][" + Arrays.toString(StarTigerContext.getActiveProfiles()) + "]");
        dataMap.put("greetings", "Hello " + visitor);
        if (traceAccess != null) {
            ListRestResponse listRestResponse = traceAccess.access();
            if (listRestResponse != null) {
                dataMap.put("next", listRestResponse.getData());
            }
        }
        return RestResponseBuilder.createSuccessRestResp(dataMap);
    }
}
