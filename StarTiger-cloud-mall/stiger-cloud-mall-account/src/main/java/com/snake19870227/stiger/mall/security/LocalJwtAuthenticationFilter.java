package com.snake19870227.stiger.mall.security;

import com.snake19870227.stiger.mall.service.AccountService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Bu HuaYang
 */
public class LocalJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(LocalJwtAuthenticationFilter.class);

    @Autowired
    private AccountService accountService;

    public LocalJwtAuthenticationFilter(JwtSignKey jwtSignKey) {
        super(jwtSignKey);
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    protected UserDetails loadUserDetails(Claims claims, String bearerJwtToken) {
        return accountService.loadAccountDetail(claims.getId());
    }
}
