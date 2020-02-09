package com.snake19870227.stiger.mall.security;

import com.snake19870227.stiger.mall.entity.bo.AccountDetail;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.manager.CloudRpcMgr;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Bu HuaYang
 */
public class CloudJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CloudJwtAuthenticationFilter.class);

    @Autowired
    private CloudRpcMgr cloudRpcMgr;

    public CloudJwtAuthenticationFilter(JwtSignKey jwtSignKey) {
        super(jwtSignKey);
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    protected AccountDetail loadUserDetails(Claims claims, String jwtToken) {

        MallAccount account = cloudRpcMgr.getAccountInfo(claims, jwtToken);

        AccountDetail accountDetail = new AccountDetail(account);
        accountDetail.setJwtToken(jwtToken);
        return accountDetail;
    }
}
