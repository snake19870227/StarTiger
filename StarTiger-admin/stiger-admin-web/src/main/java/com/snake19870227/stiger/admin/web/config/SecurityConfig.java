package com.snake19870227.stiger.admin.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import com.snake19870227.stiger.admin.web.security.WebAuthenticationFailureHandler;
import com.snake19870227.stiger.admin.web.security.WebAuthenticationSuccessHandler;
import com.snake19870227.stiger.admin.web.security.WebSecurityExceptionHandler;

/**
 * @author Bu HuaYang
 */
@Configuration
public class SecurityConfig {

    @Value("${stiger.admin.web.security.remember-me-key}")
    private String rememberMeKey;

    @Value("${stiger.admin.web.security.remember-me-cookie-name}")
    private String rememberMeCookieName;

    @Configuration
    public static class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${management.endpoints.web.base-path:/actuator}")
        private String springActuatorPath;

        @Value("${stiger.admin.web.security.remember-me-key}")
        private String rememberMeKey;

        private final WebAuthenticationSuccessHandler webAuthenticationSuccessHandler;
        private final WebAuthenticationFailureHandler webAuthenticationFailureHandler;

        private final WebSecurityExceptionHandler webSecurityExceptionHandler;

        private final RememberMeServices rememberMeServices;

        public CustomWebSecurityConfigurerAdapter(WebAuthenticationSuccessHandler webAuthenticationSuccessHandler,
                                                  WebAuthenticationFailureHandler webAuthenticationFailureHandler,
                                                  WebSecurityExceptionHandler webSecurityExceptionHandler,
                                                  RememberMeServices rememberMeServices) {
            this.webAuthenticationSuccessHandler = webAuthenticationSuccessHandler;
            this.webAuthenticationFailureHandler = webAuthenticationFailureHandler;
            this.webSecurityExceptionHandler = webSecurityExceptionHandler;
            this.rememberMeServices = rememberMeServices;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            String springActuatorPaths = springActuatorPath + "/**";

            http.csrf().ignoringAntMatchers(springActuatorPaths);
            http.headers().frameOptions().sameOrigin();

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();

            urlRegistry
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers(ProjectConstant.UrlPath.anonymousPaths()).permitAll()
                    .antMatchers(ProjectConstant.UrlPath.authenticatedPaths()).authenticated()
                    .anyRequest().access("@authAssert.canAccess(request, authentication)");

            http.formLogin()
                    .failureHandler(webAuthenticationFailureHandler)
                    .successHandler(webAuthenticationSuccessHandler)
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher(ProjectConstant.UrlPath.LOGOUT, "GET"))
                .and()
                .rememberMe()
                    .key(rememberMeKey)
                    .rememberMeServices(rememberMeServices)
                    .authenticationSuccessHandler(webAuthenticationSuccessHandler)
                .and()
                .httpBasic()
                .and().sessionManagement().maximumSessions(1);

            http.exceptionHandling()
                    .authenticationEntryPoint(webSecurityExceptionHandler)
                    .accessDeniedHandler(webSecurityExceptionHandler);
        }
    }

    @Bean
    public WebAuthenticationSuccessHandler webAuthenticationSuccessHandler(SysService sysService) {
        return new WebAuthenticationSuccessHandler(sysService);
    }

    @Bean
    public WebAuthenticationFailureHandler webAuthenticationFailureHandler() {
        return new WebAuthenticationFailureHandler();
    }

    @Bean
    public WebSecurityExceptionHandler webSecurityExceptionHandler() {
        return new WebSecurityExceptionHandler();
    }

    @Bean
    public UserDetailsManager userDetailsManager(SysService sysService) {
        return new CustomUserDetailsManager(sysService);
    }

    @Bean
    public RememberMeServices rememberMeServices(UserDetailsManager userDetailsManager) {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeKey, userDetailsManager);
        rememberMeServices.setParameter(ProjectConstant.WebAttrKey.REMEMBER_ME);
        rememberMeServices.setCookieName(rememberMeCookieName);
        return rememberMeServices;
    }
}
