package com.snake19870227.stiger.mall.manager;

import com.snake19870227.stiger.mall.entity.po.MallAccount;

/**
 * @author Bu HuaYang
 */
public interface MallAccountMgr {

    MallAccount getAccountByUsername(String username);
}
