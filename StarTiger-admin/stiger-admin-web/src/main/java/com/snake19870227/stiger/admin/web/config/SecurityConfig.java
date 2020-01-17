package com.snake19870227.stiger.admin.web.config;

import cn.hutool.core.util.ArrayUtil;
import com.snake19870227.stiger.admin.dao.mapper.SysUserMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysUserRoleMapper;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.entity.po.SysUserRole;
import com.snake19870227.stiger.admin.security.CustomUserDetailsManager;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import com.snake19870227.stiger.admin.web.security.WebSecurityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bu HuaYang
 */
@Configuration
public class SecurityConfig {

    @Configuration
    public static class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${stiger.h2.console.root-path:/h2}")
        private String h2ConsoleRootPath;

        @Value("${management.endpoints.web.base-path:/actuator}")
        private String springActuatorPath;

        @Autowired
        private WebSecurityExceptionHandler webSecurityExceptionHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            String h2ConsolePaths = h2ConsoleRootPath + "/**";
            String springActuatorPaths = springActuatorPath + "/**";

            http.csrf().ignoringAntMatchers(h2ConsolePaths, springActuatorPaths);
            http.headers().frameOptions().sameOrigin();

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();

            String[] anonymousPaths = ArrayUtil.addAll(new String[]{h2ConsolePaths}, ProjectConstant.UrlPath.anonymousPaths());
            urlRegistry
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers(anonymousPaths).permitAll()
                    .anyRequest().access("@authAssert.canAccess(request, authentication)");

            http.formLogin().loginPage(ProjectConstant.UrlPath.LOGIN)
                .and().httpBasic();

            http.exceptionHandling()
                    .authenticationEntryPoint(webSecurityExceptionHandler)
                    .accessDeniedHandler(webSecurityExceptionHandler);
            // @formatter:on
        }
    }

    @Bean
    public WebSecurityExceptionHandler webSecurityExceptionHandler() {
        return new WebSecurityExceptionHandler();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new CustomUserDetailsManager();
    }
}
