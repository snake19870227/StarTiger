package com.snake19870227.stiger.mall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.mall.manager.MallAccountMgr;
import com.snake19870227.stiger.mall.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bu HuaYang
 */
@Configuration
@ConditionalOnWebApplication
public class SecurityConfig {

    @Configuration
    @ConditionalOnWebApplication
    static class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${management.endpoints.web.base-path:/actuator}")
        private String actuatorBasePath;

        private final CloudJwtAuthenticationFilter jwtAuthenticationFilter;

        private final RestSecurityExceptionHandler restSecurityExceptionHandler;

        CustomWebSecurityConfigurerAdapter(CloudJwtAuthenticationFilter jwtAuthenticationFilter,
                                           RestSecurityExceptionHandler restSecurityExceptionHandler) {
            this.jwtAuthenticationFilter = jwtAuthenticationFilter;
            this.restSecurityExceptionHandler = restSecurityExceptionHandler;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.csrf().disable();
            http.cors();
            http.headers().frameOptions().sameOrigin();

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();
            urlRegistry.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            urlRegistry.antMatchers(actuatorBasePath + "/**").permitAll();
//            urlRegistry.anyRequest().access("@authAssert.canAccess(request, authentication)");
            urlRegistry.anyRequest().authenticated();

            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            http.exceptionHandling()
                    .authenticationEntryPoint(restSecurityExceptionHandler)
                    .accessDeniedHandler(restSecurityExceptionHandler);
        }
    }

    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder, MallAccountMgr mallAccountMgr) {
        return new CustomUserDetailsManager(passwordEncoder, mallAccountMgr);
    }

    @Bean
    public CloudJwtAuthenticationFilter jwtAuthenticationFilter(JwtSignKey jwtSignKey, RestTemplate cloudRestTemplate) {
        return new CloudJwtAuthenticationFilter(jwtSignKey, cloudRestTemplate);
    }

    @Bean
    public RestSecurityExceptionHandler restSecurityExceptionHandler(ObjectMapper objectMapper) {
        return new RestSecurityExceptionHandler(objectMapper);
    }
}
