package com.snake19870227.stiger.cloud.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.cloud.consumer.properties.StarTigerCloudProperties;
import com.snake19870227.stiger.context.StarTigerContext;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@Service
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private final StarTigerCloudProperties starTigerCloudProperties;

    private final RestTemplate restTemplate;

    public HelloService(StarTigerCloudProperties starTigerCloudProperties, RestTemplate restTemplate) {
        this.starTigerCloudProperties = starTigerCloudProperties;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    public KeyValueRestResponse hello() throws Throwable {
        String url = starTigerCloudProperties.getProducers().get("hello") + "/hello?somebody=" + StarTigerContext.getApplicationId();
        return restTemplate.getForObject(url, KeyValueRestResponse.class);
    }

    public KeyValueRestResponse helloFallback() {
        try {
            Map<String, String> dataMap = new HashMap<>(1);
            dataMap.put("title", StarTigerContext.getMessage("template.0001", StarTigerContext.getApplicationId()));
            return RestResponseBuilder.createFailureRestResp(dataMap, KeyValueRestResponse.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error(e.getLocalizedMessage(), e);
            return null;
        }
    }
}
