package com.snake19870227.stiger.admin.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.api.security.JwtAuthenticationFilter;
import com.snake19870227.stiger.admin.api.security.JwtRsaSignKey;
import com.snake19870227.stiger.admin.api.security.JwtSignKey;
import com.snake19870227.stiger.admin.api.security.LoadUsernameAndPasswordFilter;
import com.snake19870227.stiger.admin.api.security.RestAccessDeniedHandler;
import com.snake19870227.stiger.admin.api.security.RestAuthenticationEntryPoint;
import com.snake19870227.stiger.admin.api.security.RestAuthenticationFailureHandler;
import com.snake19870227.stiger.admin.api.security.RestAuthenticationSuccessHandler;
import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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

        private JwtAuthenticationFilter jwtAuthenticationFilter;

        private AuthenticationSuccessHandler authenticationSuccessHandler;

        private AuthenticationFailureHandler authenticationFailureHandler;

        private AuthenticationEntryPoint authenticationEntryPoint;

        private AccessDeniedHandler accessDeniedHandler;

        CustomWebSecurityConfigurerAdapter(LoadUsernameAndPasswordFilter loadUsernameAndPasswordFilter,
                                           JwtAuthenticationFilter jwtAuthenticationFilter,
                                           AuthenticationSuccessHandler authenticationSuccessHandler,
                                           AuthenticationFailureHandler authenticationFailureHandler,
                                           AuthenticationEntryPoint authenticationEntryPoint,
                                           AccessDeniedHandler accessDeniedHandler) {
            this.loadUsernameAndPasswordFilter = loadUsernameAndPasswordFilter;
            this.jwtAuthenticationFilter = jwtAuthenticationFilter;
            this.authenticationSuccessHandler = authenticationSuccessHandler;
            this.authenticationFailureHandler = authenticationFailureHandler;
            this.authenticationEntryPoint = authenticationEntryPoint;
            this.accessDeniedHandler = accessDeniedHandler;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            String h2ConsolePaths = h2ConsoleRootPath + "/**";

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.csrf().disable();
            http.cors();
            http.headers().frameOptions().sameOrigin();

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();
            urlRegistry.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            urlRegistry.antMatchers(h2ConsolePaths).permitAll();
            urlRegistry.anyRequest().access("@authAssert.canAccess(request, authentication)");

            http.formLogin()
                    .loginProcessingUrl(LOGIN_PROCESSING_URL)
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler);

            http.addFilterBefore(loadUsernameAndPasswordFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            http.exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler);
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

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(ObjectMapper objectMapper,
                                                           JwtSignKey jwtSignKey,
                                                           AuthenticationEntryPoint authenticationEntryPoint,
                                                           UserDetailsManager userDetailsManager) {
        return new JwtAuthenticationFilter(jwtSignKey, objectMapper, authenticationEntryPoint, userDetailsManager);
    }

    @Bean
    public JwtSignKey jwtSignKey() {
        return new JwtRsaSignKey();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler();
    }
}
