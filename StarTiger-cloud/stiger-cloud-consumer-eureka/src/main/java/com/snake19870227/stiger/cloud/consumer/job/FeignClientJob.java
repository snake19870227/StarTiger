package com.snake19870227.stiger.cloud.consumer.job;

import com.snake19870227.stiger.cloud.consumer.config.ProjectConfig;
import com.snake19870227.stiger.cloud.consumer.http.RestStringMapTypeReference;
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

    private ProjectConfig.FeignModeConfig.HelloService helloService;

    public FeignClientJob(ProjectConfig.FeignModeConfig.HelloService helloService) {
        this.helloService = helloService;
    }

    @Scheduled(cron = "* * * * * ?")
    public void doRequest() throws Throwable {
        RestResponse<Map<String, String>> response = helloService.hello();
        logger.info(response.getData().toString());
    }
}
