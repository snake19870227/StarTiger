package com.snake19870227.stiger.admin.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.api.security.LoadUsernameAndPasswordFilter;
import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Bu HuaYang
 */
@Configuration
public class SecurityConfig {

    public static final String LOGIN_PROCESSING_URL = "/process";
    public static final String LOGIN_PRE_PATH = "/login";
    public static final String LOGIN_FAILURE_PATH = "/failure";
    public static final String LOGIN_SUCCESS_PATH = "/success";

    public static final String LOGIN_FAILURE_URL = LOGIN_PRE_PATH + LOGIN_FAILURE_PATH;
    public static final String LOGIN_SUCCESS_URL = LOGIN_PRE_PATH + LOGIN_SUCCESS_PATH;

    @Configuration
    static class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${stiger.h2.console.root-path:/h2}")
        private String h2ConsoleRootPath;

        private LoadUsernameAndPasswordFilter loadUsernameAndPasswordFilter;

        CustomWebSecurityConfigurerAdapter(LoadUsernameAndPasswordFilter loadUsernameAndPasswordFilter) {
            this.loadUsernameAndPasswordFilter = loadUsernameAndPasswordFilter;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            String h2ConsolePaths = h2ConsoleRootPath + "/**";

            http.csrf().disable();
            http.cors();
            http.headers().frameOptions().sameOrigin();

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();
            urlRegistry.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            urlRegistry.antMatchers(h2ConsolePaths).permitAll();
            urlRegistry.anyRequest().authenticated();

            http.formLogin()
                    .loginProcessingUrl(LOGIN_PROCESSING_URL)
                    .successForwardUrl(LOGIN_SUCCESS_URL)
                    .failureForwardUrl(LOGIN_FAILURE_URL);

            http.addFilterBefore(loadUsernameAndPasswordFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new CustomUserDetailsManager();
    }

    @Bean
    public LoadUsernameAndPasswordFilter loadUsernameAndPasswordFilter(ObjectMapper objectMapper) {
        return new LoadUsernameAndPasswordFilter(objectMapper);
    }
}
