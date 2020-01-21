package com.snake19870227.stiger.cloud.consumer.service;

import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Bu HuaYang
 */
@Service
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private final RestTemplate restTemplate;

    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @HystrixCommand(fallbackMethod = "helloFallback")
    public KeyValueRestResponse hello() throws Throwable {
        String url = "http://producer-zookeeper/hello";
        return restTemplate.getForObject(url, KeyValueRestResponse.class);
    }

    public KeyValueRestResponse helloFallback() {
        try {
            return RestResponseBuilder.createFailureRestResp(null, KeyValueRestResponse.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error(e.getLocalizedMessage(), e);
            return null;
        }
    }
}
