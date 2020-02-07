package com.snake19870227.stiger.mall.manager;

import com.snake19870227.stiger.mall.entity.po.MallAccount;
import io.jsonwebtoken.Claims;

/**
 * @author Bu HuaYang
 */
public interface CloudRpcMgr {

    MallAccount getAccountInfo(Claims claims, String jwtToken);
}
