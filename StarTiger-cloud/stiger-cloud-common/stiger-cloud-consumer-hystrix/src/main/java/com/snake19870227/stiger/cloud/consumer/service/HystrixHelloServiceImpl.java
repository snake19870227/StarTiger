package com.snake19870227.stiger.cloud.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;
import com.snake19870227.stiger.cloud.base.properties.StarTigerCloudProperties;
import com.snake19870227.stiger.core.StarTigerContext;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@Service
public class HystrixHelloServiceImpl extends HelloServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(HystrixHelloServiceImpl.class);

    public HystrixHelloServiceImpl(StarTigerCloudProperties starTigerCloudProperties, RestTemplate restTemplate) {
        super(starTigerCloudProperties, restTemplate);
    }

    @Override
    @HystrixCommand(fallbackMethod = "helloFallback")
    public MapRestResponse hello() {
        return super.hello();
    }

    @Override
    public MapRestResponse helloFallback() {
        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("title", StarTigerContext.getMessage("tp.consumer.0001", StarTigerContext.getApplicationId()));
        return RestResponseBuilder.createFailureRestResp(dataMap, MapRestResponse.class);
    }
}
