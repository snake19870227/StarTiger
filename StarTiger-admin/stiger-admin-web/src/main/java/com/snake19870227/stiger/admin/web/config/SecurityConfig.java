package com.snake19870227.stiger.admin.web.config;

import com.snake19870227.stiger.admin.web.dao.mapper.SysUserMapper;
import com.snake19870227.stiger.admin.web.dao.mapper.SysUserRoleMapper;
import com.snake19870227.stiger.admin.web.entity.po.SysUser;
import com.snake19870227.stiger.admin.web.entity.po.SysUserRole;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

//    private SysRoleMapper sysRoleMapper;
//    private SysResourceMapper sysResourceMapper;
//    private SysRoleResourceMapper sysRoleResourceMapper;

//    public SecurityConfig(SysRoleMapper sysRoleMapper, SysResourceMapper sysResourceMapper, SysRoleResourceMapper sysRoleResourceMapper) {
//        this.sysRoleMapper = sysRoleMapper;
//        this.sysResourceMapper = sysResourceMapper;
//        this.sysRoleResourceMapper = sysRoleResourceMapper;
//    }

//    @Bean
//    public AccessDecisionManager affirmativeBased() {
//        return new AffirmativeBased(Arrays.asList(new RoleVoter(), new WebExpressionVoter()));
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/h2/**")
                .and().headers().frameOptions().sameOrigin()
                .and().authorizeRequests()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .antMatchers("/h2/**").permitAll()
//                    .antMatchers("/res2").hasRole("Admin3")
                        .anyRequest().authenticated()
//                        .withObjectPostProcessor(filterSecurityInterceptorObjectPostProcessor())
                .and().formLogin()
        ;
    }

    //    private ObjectPostProcessor<FilterSecurityInterceptor> filterSecurityInterceptorObjectPostProcessor() {
//        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
//            @Override
//            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//
//                FilterInvocationSecurityMetadataSource parentMetadataSource = object.getSecurityMetadataSource();
//
//                CustomFilterInvocationSecurityMetadataSource metadataSource
//                        = new CustomFilterInvocationSecurityMetadataSource(sysRoleMapper, sysResourceMapper, sysRoleResourceMapper);
//                metadataSource.setParentMetadataSource(parentMetadataSource);
//
//                object.setAccessDecisionManager(affirmativeBased());
//                object.setSecurityMetadataSource(metadataSource);
//
//                return object;
//            }
//        };
//    }

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
