package com.snake19870227.stiger.mall.config;

import cn.hutool.crypto.asymmetric.RSA;
import com.snake19870227.stiger.mall.security.JwtSignKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Key;
import java.security.KeyPair;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableConfigurationProperties({
        StarTigerMallSecurityProperties.class,
        StarTigerMallServiceProperties.class,
        StarTigerMallPurchaseProperties.class
})
public class CommonConfig {

    private static final Logger logger = LoggerFactory.getLogger(CommonConfig.class);

    private final StarTigerMallSecurityProperties securityProperties;

    public CommonConfig(StarTigerMallSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        logger.debug("创建 {}", this.getClass().getName());
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
