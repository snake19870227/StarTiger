package com.snake19870227.stiger.mall.security;

import com.snake19870227.stiger.mall.service.AccountService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
    protected AccountDetail loadUserDetails(Claims claims, String jwtToken) {
        AccountDetail accountDetail = accountService.loadAccountDetail(claims.getId());
        accountDetail.setJwtToken(jwtToken);
        return accountDetail;
    }
}
