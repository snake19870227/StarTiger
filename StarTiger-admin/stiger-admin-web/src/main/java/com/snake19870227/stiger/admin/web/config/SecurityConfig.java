package com.snake19870227.stiger.admin.web.config;

import com.snake19870227.stiger.admin.dao.mapper.SysUserMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysUserRoleMapper;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.entity.po.SysUserRole;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${stiger.h2.console.root-path:/h2}")
    private String h2ConsoleRootPath;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String h2ConsolePaths = h2ConsoleRootPath + "/**";

        http.csrf().ignoringAntMatchers(h2ConsolePaths);
        http.headers().frameOptions().sameOrigin();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();
        urlRegistry.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
        urlRegistry.antMatchers(h2ConsolePaths).permitAll();
        urlRegistry.anyRequest().access("@authAssert.canAccess(httpServletRequest, authentication)");

        http.formLogin();
    }

    @Bean
    public UserDetailsManager userDetailsManager(SysUserMapper sysUserMapper,
                                                 SysUserRoleMapper sysUserRoleMapper) {
        return new UserDetailsManager() {
            @Override
            public void createUser(UserDetails user) {

            }

            @Override
            public void updateUser(UserDetails user) {

            }

            @Override
            public void deleteUser(String username) {

            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {

            }

            @Override
            public boolean userExists(String username) {
                SysUser sysUser = sysUserMapper.queryByUsername(username);
                return sysUser != null;
            }

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                SysUser sysUser = sysUserMapper.queryByUsername(username);
                if (sysUser == null) {
                    return null;
                }
                List<SysUserRole> sysUserRoleList = sysUserRoleMapper.queryByUserId(sysUser.getUserId());
                List<GrantedAuthority> roleCodeList = sysUserRoleList.stream()
                                                                     .map(sysUserRole -> new SimpleGrantedAuthority(sysUserRole.getRoleCode()))
                                                                     .collect(Collectors.toList());
                return User.withUsername(sysUser.getUsername())
                           .password(sysUser.getEncodePassword())
                           .authorities(roleCodeList.isEmpty() ? AuthorityUtils.NO_AUTHORITIES : roleCodeList).build();
            }
        };
    }
}
