package com.snake19870227.stiger.admin.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.UserDetailsManager;
import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import com.snake19870227.stiger.admin.security.JwtRsaSignKey;
import com.snake19870227.stiger.admin.security.JwtSignKey;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.web.exception.GlobalHandlerExceptionResolver;

/**
 * @author Bu HuaYang
 */
@Configuration
public class ProjectConfig {

    @Bean
    public GlobalHandlerExceptionResolver exceptionResolver() {
        return new GlobalHandlerExceptionResolver();
    }

    @Bean
    public UserDetailsManager userDetailsManager(SysService sysService) {
        return new CustomUserDetailsManager(sysService);
    }

    @Bean
    public JwtSignKey jwtSignKey() {
        return new JwtRsaSignKey();
    }
}
