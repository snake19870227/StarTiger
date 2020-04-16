package com.snake19870227.stiger.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/15
 */
@SpringBootApplication
public class StarTigerOauth2ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarTigerOauth2ResourceApplication.class, args);
    }

    @Configuration
    @EnableResourceServer
    public static class Config extends ResourceServerConfigurerAdapter {

        private final ResourceServerProperties resourceServerProperties;
        private final TokenStore tokenStore;

        public Config(ResourceServerProperties resourceServerProperties, TokenStore tokenStore) {
            this.resourceServerProperties = resourceServerProperties;
            this.tokenStore = tokenStore;
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.tokenStore(tokenStore)
                    .resourceId(resourceServerProperties.getResourceId());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            http.csrf().disable();
        }
    }

    @Configuration
    public static class TokenConfig {

        private final ResourceServerProperties resourceServerProperties;

        public TokenConfig(ResourceServerProperties resourceServerProperties) {
            this.resourceServerProperties = resourceServerProperties;
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setVerifierKey(resourceServerProperties.getJwt().getKeyValue());
            return converter;
        }

        @Bean
        public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
            return new JwtTokenStore(jwtAccessTokenConverter);
        }
    }

    @RestController
    public static class ResourceController {

        private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

        @GetMapping(path = "/hello")
        public String hello() {
            return "hello";
        }
    }
}
