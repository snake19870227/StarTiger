package com.snake19870227.stiger.mall.security;

import com.snake19870227.stiger.mall.service.AccountService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Bu HuaYang
 */
public class LocalJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {

    @Autowired
    private AccountService accountService;

    public LocalJwtAuthenticationFilter(JwtSignKey jwtSignKey) {
        super(jwtSignKey);
    }

    @Override
    protected UserDetails loadUserDetails(Claims claims, String bearerJwtToken) {
        return accountService.loadAccountDetail(claims.getId());
    }
}
