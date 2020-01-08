package com.snake19870227.stiger.admin.security;

import com.snake19870227.stiger.admin.dao.mapper.SysUserMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysUserRoleMapper;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.entity.po.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Bu HuaYang
 */
public class CustomUserDetailsManager implements UserDetailsManager {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

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
        return sysUserMapper.queryByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SysUser> sysUserObj = sysUserMapper.queryByUsername(username);
        Optional<UserDetails> userDetailsObj = sysUserObj.map(sysUser -> {
            List<SysUserRole> sysUserRoleList = sysUserRoleMapper.queryByUserId(sysUser.getUserId());
            List<GrantedAuthority> roleCodeList = sysUserRoleList.stream()
                    .map(sysUserRole -> new SimpleGrantedAuthority(sysUserRole.getRoleCode()))
                    .collect(Collectors.toList());
            return User.withUsername(sysUser.getUsername())
                    .password(sysUser.getEncodePassword())
                    .authorities(roleCodeList.isEmpty() ? AuthorityUtils.NO_AUTHORITIES : roleCodeList).build();
        });
        return userDetailsObj.orElse(null);
    }
}
