package com.snake19870227.stiger.oauth2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/14
 */
@Configuration
@EnableAuthorizationServer
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {

        private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

        @Override
        @Bean
        public UserDetailsService userDetailsService() {
            return new InMemoryUserDetailsManager(
                    User.withDefaultPasswordEncoder()
                            .username("user1")
                            .password("123")
                            .roles("any")
                            .build()
            );
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/callback", "/oauth/token", "/oauth/check_token").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .httpBasic()
            ;
        }
    }

    @Configuration
    public static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

        final PasswordEncoder passwordEncoder;

        public AuthorizationServerConfig(
                PasswordEncoder passwordEncoder
        ) {
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("client1")
                    .secret(passwordEncoder.encode("123"))
                    .authorizedGrantTypes("authorization_code", "password")
                    .scopes("any", "read")
                    .redirectUris(
                            "http://localhost:8880/oauthServer/callback",
                            "http://localhost:8881/oauthClient/login/oauth2/code/demo"
                    )
            ;
        }

//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//            endpoints.authenticationManager(this.authenticationManager)
//            ;
//        }

//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//            security.allowFormAuthenticationForClients()
//                    .checkTokenAccess("isAuthenticated()");
//        }
    }
}
