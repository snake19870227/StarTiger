package com.snake19870227.stiger.admin.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;
import com.snake19870227.stiger.admin.dao.mapper.SysUserMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysUserRoleMapper;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.opt.UserInfoOpt;

/**
 * @author Bu HuaYang
 */
@Transactional(rollbackFor = Exception.class)
public class CustomUserDetailsManager implements UserDetailsManager {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private UserInfoOpt userInfoOpt;

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
        Optional<UserDetails> userDetailsObj
                = sysUserObj.map(sysUser -> {
            UserInfo userInfo = userInfoOpt.loadUserInfo(sysUser.getUserFlow());
            List<GrantedAuthority> roleCodeList = userInfo.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleCode()))
                    .collect(Collectors.toList());
            return User.withUsername(sysUser.getUsername())
                    .password(sysUser.getEncodePassword())
                    .authorities(roleCodeList.isEmpty() ? AuthorityUtils.NO_AUTHORITIES : roleCodeList).build();
        });
        return userDetailsObj.orElse(null);
    }
}
