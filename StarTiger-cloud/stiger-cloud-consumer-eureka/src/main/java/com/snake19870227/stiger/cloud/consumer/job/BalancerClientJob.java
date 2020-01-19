package com.snake19870227.stiger.cloud.consumer.job;

import com.snake19870227.stiger.http.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bu HuaYang
 */
@Profile("balancer")
@Component
public class BalancerClientJob {

    @Autowired
    private LoadBalancerClient client;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "* * * * * ?")
    public void doRequest() {
        ServiceInstance instance = client.choose("producer-eureka");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello";
        RestResponse response = restTemplate.getForObject(url, RestResponse.class);
        System.out.println(response.getData());
    }
}
