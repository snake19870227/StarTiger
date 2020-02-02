package com.snake19870227.stiger.cloud.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;
import com.snake19870227.stiger.cloud.base.properties.StarTigerCloudProperties;
import com.snake19870227.stiger.context.StarTigerContext;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@Component
public class NextAccessInvoker {

    private static final Logger logger = LoggerFactory.getLogger(NextAccessInvoker.class);

    private final StarTigerCloudProperties starTigerCloudProperties;

    private final RestTemplate restTemplate;

    public NextAccessInvoker(StarTigerCloudProperties starTigerCloudProperties, RestTemplate restTemplate) {
        this.starTigerCloudProperties = starTigerCloudProperties;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "accessFail")
    public MapRestResponse accessNextService(String serviceName) {
        if (starTigerCloudProperties.getProducers().containsKey(serviceName)) {
            String url = starTigerCloudProperties.getProducers().get(serviceName);
            return restTemplate.getForObject(url + "?visitor=" + StarTigerContext.getApplicationName(), MapRestResponse.class);
        } else {
            Map<String, Object> failDataMap = new HashMap<>();
            failDataMap.put("error", StarTigerContext.getMessage("tp.consumer.0002", serviceName));
            return RestResponseBuilder.createFailureRestResp(failDataMap, MapRestResponse.class);
        }
    }

    private MapRestResponse accessFail(String serviceName) {
        Map<String, Object> failDataMap = new HashMap<>();
        failDataMap.put("error", StarTigerContext.getMessage("tp.consumer.0003", serviceName));
        return RestResponseBuilder.createFailureRestResp(failDataMap, MapRestResponse.class);
    }
}
