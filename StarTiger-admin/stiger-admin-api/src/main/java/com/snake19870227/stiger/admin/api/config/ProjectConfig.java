package com.snake19870227.stiger.admin.api.config;

import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import com.snake19870227.stiger.admin.security.JwtRsaSignKey;
import com.snake19870227.stiger.admin.security.JwtSignKey;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author Bu HuaYang
 */
@Configuration
public class ProjectConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("base-messages", "proj-messages");
        return messageSource;
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new CustomUserDetailsManager();
    }

    @Bean
    public JwtSignKey jwtSignKey() {
        return new JwtRsaSignKey();
    }
}
