package com.snake19870227.stiger.mall.manager;

import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import io.jsonwebtoken.Claims;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface CloudRpcMgr {

    MallAccount getAccountInfo(Claims claims, String jwtToken);

    List<MallGoods> getGoodsList(List<String> goodsIdList, String jwtToken);
}
