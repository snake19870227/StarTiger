package com.snake19870227.stiger.admin.security;

import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.core.StarTigerConstant;

/**
 * @author Bu HuaYang
 */
public class CustomUserDetailsManager implements UserDetailsManager {

    private final SysService sysService;

    public CustomUserDetailsManager(SysService sysService) {
        this.sysService = sysService;
    }

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
        return sysService.getUserByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = sysService.loadUserInfoByUsername(username);

        if (userInfo == null) {
            throw new UsernameNotFoundException(StrUtil.format("未找到用户名[{}]对应的账户", username));
        }

        List<GrantedAuthority> roleCodeList = userInfo.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(StarTigerConstant.SPRING_SECURITY_ROLE_PREFIX + role.getRoleCode()))
                .collect(Collectors.toList());
        return User.withUsername(userInfo.getUser().getUsername())
                .password(userInfo.getUser().getEncodePassword())
                .authorities(roleCodeList.isEmpty() ? AuthorityUtils.NO_AUTHORITIES : roleCodeList).build();
    }
}
