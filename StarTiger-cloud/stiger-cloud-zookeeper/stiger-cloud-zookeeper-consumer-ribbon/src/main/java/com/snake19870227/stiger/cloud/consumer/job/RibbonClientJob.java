package com.snake19870227.stiger.cloud.consumer.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.cloud.consumer.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Bu HuaYang
 */
@Component
public class RibbonClientJob {

    private static final Logger logger = LoggerFactory.getLogger(RibbonClientJob.class);

    private final ObjectMapper objectMapper;

    private final HelloService helloService;

    public RibbonClientJob(ObjectMapper objectMapper, HelloService helloService) {
        this.objectMapper = objectMapper;
        this.helloService = helloService;
    }

    @Scheduled(cron = "* * * * * ?")
    public void doRequest() throws Throwable {
        KeyValueRestResponse response = helloService.hello();
        logger.info(
                objectMapper.writeValueAsString(
                        Optional.ofNullable(response)
                                .orElseThrow((Supplier<Throwable>) () -> new Exception("Can't load response."))
                )
        );
    }
}
