package com.snake19870227.stiger.cloud.consumer.job;

import com.snake19870227.stiger.cloud.base.http.RestStringMapTypeReference;
import com.snake19870227.stiger.core.StarTigerContext;
import com.snake19870227.stiger.core.restful.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
@Component
public class BalancerClientJob {

    private static final Logger logger = LoggerFactory.getLogger(BalancerClientJob.class);

    private final LoadBalancerClient client;

    private final RestTemplate restTemplate;

    public BalancerClientJob(LoadBalancerClient client) {
        this.client = client;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(cron = "* * * * * ?")
    public void doRequest() throws Throwable {
        ServiceInstance instance = client.choose("producer-eureka");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello?somebody=" + StarTigerContext.getApplicationId();
        RestStringMapTypeReference typeReference = new RestStringMapTypeReference();
        ResponseEntity<RestResponse<Map<String, String>>> response = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);
        logger.info(
                Optional.ofNullable(response.getBody())
                        .orElseThrow((Supplier<Throwable>) () -> new Exception("Can't load response."))
                        .getData().toString()
        );
    }
}
