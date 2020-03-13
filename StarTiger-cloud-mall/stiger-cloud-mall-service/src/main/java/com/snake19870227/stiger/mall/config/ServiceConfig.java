package com.snake19870227.stiger.mall.config;

import com.snake19870227.stiger.web.exception.GlobalHandlerExceptionResolver;
import com.snake19870227.stiger.mall.remote.MallCloudRpcService;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableSwagger2Doc
@MapperScan(basePackages = {
        "com.snake19870227.stiger.mall.mapper"
})
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

    private final StarTigerMallServiceProperties serviceProperties;

    public ServiceConfig(StarTigerMallServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Bean
    public GlobalHandlerExceptionResolver mvcHandlerExceptionResolver() {
        return new GlobalHandlerExceptionResolver();
    }

    @LoadBalanced
    @Bean
    public RestTemplate cloudRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(1L))
                .setReadTimeout(Duration.ofSeconds(5L))
                .build();
    }

    @Bean
    public MallCloudRpcService mallCloudRpcService() {
        return new MallCloudRpcService(serviceProperties, cloudRestTemplate());
    }
}
