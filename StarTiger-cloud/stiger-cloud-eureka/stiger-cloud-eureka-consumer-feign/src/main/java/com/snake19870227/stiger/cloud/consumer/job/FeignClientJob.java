package com.snake19870227.stiger.cloud.consumer.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;
import com.snake19870227.stiger.cloud.consumer.remote.HelloService;
import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang
 */
@Component
public class FeignClientJob {

    private static final Logger logger = LoggerFactory.getLogger(FeignClientJob.class);

    private final ObjectMapper objectMapper;

    private final HelloService helloService;

    public FeignClientJob(ObjectMapper objectMapper, HelloService helloService) {
        this.objectMapper = objectMapper;
        this.helloService = helloService;
    }

    @Scheduled(cron = "* * * * * ?")
    public void doRequest() throws JsonProcessingException {
        MapRestResponse response = helloService.hello(StarTigerContext.getApplicationId());
        logger.info(objectMapper.writeValueAsString(response));
    }
}
