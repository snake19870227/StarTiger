package com.snake19870227.stiger.mall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.mall.common.StarTigerMallSecurityProperties;
import com.snake19870227.stiger.mall.manager.MallAccountMgr;
import com.snake19870227.stiger.mall.security.*;
import com.snake19870227.stiger.mall.security.LoadUsernameAndPasswordFilter;
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

/**
 * @author Bu HuaYang
 */
@Configuration
@ConditionalOnWebApplication
public class SecurityConfig {

    private final StarTigerMallSecurityProperties securityProperties;

    public SecurityConfig(StarTigerMallSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Configuration
    @ConditionalOnWebApplication
    static class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${management.endpoints.web.base-path:/actuator}")
        private String actuatorBasePath;

        private final LoadUsernameAndPasswordFilter loadUsernameAndPasswordFilter;

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        private final RestAuthenticationHandler restAuthenticationHandler;

        private final RestSecurityExceptionHandler restSecurityExceptionHandler;

        private final StarTigerMallSecurityProperties securityProperties;

        CustomWebSecurityConfigurerAdapter(LoadUsernameAndPasswordFilter loadUsernameAndPasswordFilter,
                                           JwtAuthenticationFilter jwtAuthenticationFilter,
                                           RestAuthenticationHandler restAuthenticationHandler,
                                           RestSecurityExceptionHandler restSecurityExceptionHandler,
                                           StarTigerMallSecurityProperties securityProperties) {
            this.loadUsernameAndPasswordFilter = loadUsernameAndPasswordFilter;
            this.jwtAuthenticationFilter = jwtAuthenticationFilter;
            this.restAuthenticationHandler = restAuthenticationHandler;
            this.restSecurityExceptionHandler = restSecurityExceptionHandler;
            this.securityProperties = securityProperties;
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

            http.formLogin()
                    .loginProcessingUrl(securityProperties.getLoginProcessingUrl())
                    .successHandler(restAuthenticationHandler)
                    .failureHandler(restAuthenticationHandler);

            http.httpBasic();

            http.addFilterBefore(loadUsernameAndPasswordFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

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
    public LoadUsernameAndPasswordFilter loadUsernameAndPasswordFilter(ObjectMapper objectMapper) {
        return new LoadUsernameAndPasswordFilter(objectMapper, securityProperties);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtSignKey jwtSignKey) {
        return new JwtAuthenticationFilter(jwtSignKey);
    }

    @Bean
    public RestAuthenticationHandler restAuthenticationHandler(JwtSignKey jwtSignKey, ObjectMapper objectMapper) {
        return new RestAuthenticationHandler(securityProperties, jwtSignKey, objectMapper);
    }

    @Bean
    public RestSecurityExceptionHandler restSecurityExceptionHandler(ObjectMapper objectMapper) {
        return new RestSecurityExceptionHandler(objectMapper);
    }
}
