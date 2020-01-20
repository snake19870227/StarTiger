package com.snake19870227.stiger.cloud.consumer.job;

import com.snake19870227.stiger.cloud.consumer.http.RestStringMapTypeReference;
import com.snake19870227.stiger.http.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
@Profile("ribbon")
@Component
public class RibbonClientJob {

    private static final Logger logger = LoggerFactory.getLogger(RibbonClientJob.class);

    private final RestTemplate restTemplate;

    public RibbonClientJob(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "* * * * * ?")
    public void doRequest() throws Throwable {
        String url = "http://producer-eureka/hello";
        RestStringMapTypeReference typeReference = new RestStringMapTypeReference();
        ResponseEntity<RestResponse<Map<String, String>>> response = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);
        logger.info(
                Optional.ofNullable(response.getBody())
                        .orElseThrow((Supplier<Throwable>) () -> new Exception("Can't load response."))
                        .getData().toString()
        );
    }
}
