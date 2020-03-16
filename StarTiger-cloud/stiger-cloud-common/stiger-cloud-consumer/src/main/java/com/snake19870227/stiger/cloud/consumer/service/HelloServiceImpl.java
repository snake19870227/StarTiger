package com.snake19870227.stiger.cloud.consumer.service;

import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;
import com.snake19870227.stiger.cloud.base.properties.StarTigerCloudProperties;
import com.snake19870227.stiger.cloud.base.service.HelloService;
import com.snake19870227.stiger.core.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bu HuaYang
 */
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    protected final StarTigerCloudProperties starTigerCloudProperties;

    protected final RestTemplate restTemplate;

    public HelloServiceImpl(StarTigerCloudProperties starTigerCloudProperties, RestTemplate restTemplate) {
        this.starTigerCloudProperties = starTigerCloudProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public MapRestResponse hello() {
        String url = starTigerCloudProperties.getProducers().get("hello") + "/hello?somebody=" + StarTigerContext.getApplicationId();
        return restTemplate.getForObject(url, MapRestResponse.class);
    }

    @Override
    public MapRestResponse helloFallback() {
        return null;
    }
}
