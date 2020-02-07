package com.snake19870227.stiger.mall.config;

import cn.hutool.crypto.asymmetric.RSA;
import com.snake19870227.stiger.context.GlobalHandlerExceptionResolver;
import com.snake19870227.stiger.mall.common.StarTigerMallSecurityProperties;
import com.snake19870227.stiger.mall.common.StarTigerMallServiceProperties;
import com.snake19870227.stiger.mall.security.JwtSignKey;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.security.KeyPair;
import java.time.Duration;

/**
 * @author Bu HuaYang
 */
@Configuration
@MapperScan(basePackages = {
        "com.snake19870227.stiger.mall.mapper"
})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties({
        StarTigerMallSecurityProperties.class,
        StarTigerMallServiceProperties.class
})
public class CommonConfig {

    private static final Logger logger = LoggerFactory.getLogger(CommonConfig.class);

    private final StarTigerMallSecurityProperties securityProperties;

    public CommonConfig(StarTigerMallSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
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
    public JwtSignKey jwtSignKey() {

        JwtSignKey jwtSignKey = new JwtSignKey() {

            protected KeyPair keyPair;

            @Override
            public void init() {
                RSA rsa = new RSA(securityProperties.getPrivateKey(), securityProperties.getPublicKey());
                this.keyPair = new KeyPair(rsa.getPublicKey(), rsa.getPrivateKey());
            }

            @Override
            public Key getSignKey() {
                return keyPair.getPrivate();
            }

            @Override
            public Key getSigningKey() {
                return keyPair.getPublic();
            }
        };

        jwtSignKey.init();

        return jwtSignKey;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
