package com.snake19870227.stiger.admin.api.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.UserDetailsManager;
import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import com.snake19870227.stiger.admin.security.JwtRsaSignKey;
import com.snake19870227.stiger.admin.security.JwtSignKey;
import com.snake19870227.stiger.admin.service.sys.SysUserService;
import com.snake19870227.stiger.web.exception.GlobalHandlerExceptionResolver;
import com.snake19870227.stiger.web.exception.PostWebErrorHandler;

/**
 * @author Bu HuaYang
 */
@Configuration
public class ProjectConfig {

    @Bean
    public GlobalHandlerExceptionResolver exceptionResolver(ObjectProvider<PostWebErrorHandler> postWebErrorHandlers) {
        return new GlobalHandlerExceptionResolver(postWebErrorHandlers);
    }

    @Bean
    public UserDetailsManager userDetailsManager(SysUserService sysUserService) {
        return new CustomUserDetailsManager(sysUserService);
    }

    @Bean
    public JwtSignKey jwtSignKey() {
        return new JwtRsaSignKey();
    }
}
