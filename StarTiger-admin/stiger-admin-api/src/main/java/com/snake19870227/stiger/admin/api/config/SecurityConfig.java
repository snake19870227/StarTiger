package com.snake19870227.stiger.admin.api.config;

import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author Bu HuaYang
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${stiger.h2.console.root-path:/h2}")
    private String h2ConsoleRootPath;

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

        http.formLogin().loginProcessingUrl("/process").successForwardUrl("/login/success").failureForwardUrl("/login/failure");
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new CustomUserDetailsManager();
    }
}
