package com.snake19870227.stiger.mall.security;

import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.manager.MallAccountMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Bu HuaYang
 */
@Transactional(rollbackFor = Exception.class)
public class CustomUserDetailsManager implements UserDetailsManager {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsManager.class);

    private final PasswordEncoder passwordEncoder;

    private final MallAccountMgr mallAccountMgr;

    public CustomUserDetailsManager(PasswordEncoder passwordEncoder, MallAccountMgr mallAccountMgr) {
        this.passwordEncoder = passwordEncoder;
        this.mallAccountMgr = mallAccountMgr;
        logger.debug("创建 {}", this.getClass().getName());
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
        return Optional.ofNullable(mallAccountMgr.getAccountByUsername(username)).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MallAccount> account = Optional.ofNullable(mallAccountMgr.getAccountByUsername(username));
        return account.map(AccountDetail::new).orElse(null);
    }
}
