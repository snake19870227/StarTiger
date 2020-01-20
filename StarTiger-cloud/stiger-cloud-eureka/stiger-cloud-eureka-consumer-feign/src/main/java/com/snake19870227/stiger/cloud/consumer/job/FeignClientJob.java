package com.snake19870227.stiger.cloud.consumer.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.cloud.consumer.config.ProjectConfig;
import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.cloud.consumer.http.RestStringMapTypeReference;
import com.snake19870227.stiger.cloud.consumer.remote.HelloService;
import com.snake19870227.stiger.http.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Bu HuaYang
 */
@Profile("feign")
@Component
public class FeignClientJob {

    private static final Logger logger = LoggerFactory.getLogger(FeignClientJob.class);

    private ObjectMapper objectMapper;

    private HelloService helloService;

    public FeignClientJob(ObjectMapper objectMapper, HelloService helloService) {
        this.objectMapper = objectMapper;
        this.helloService = helloService;
    }

    @Scheduled(cron = "* * * * * ?")
    public void doRequest() throws JsonProcessingException {
        KeyValueRestResponse response = helloService.hello();
        logger.info(objectMapper.writeValueAsString(response));
    }
}
