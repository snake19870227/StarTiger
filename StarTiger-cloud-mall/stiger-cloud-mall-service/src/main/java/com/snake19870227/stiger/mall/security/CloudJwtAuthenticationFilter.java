package com.snake19870227.stiger.mall.security;

import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.remote.MallCloudRpcService;
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
    private MallCloudRpcService mallCloudRpcService;

    public CloudJwtAuthenticationFilter(JwtSignKey jwtSignKey) {
        super(jwtSignKey);
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    protected AccountDetail loadUserDetails(Claims claims, String jwtToken) {

        MallAccount account = mallCloudRpcService.getAccountInfo(claims, jwtToken);

        AccountDetail accountDetail = new AccountDetail(account);
        accountDetail.setJwtToken(jwtToken);
        return accountDetail;
    }
}
