package com.snake19870227.stiger.mall.service;

import com.snake19870227.stiger.mall.security.AccountDetail;
import com.snake19870227.stiger.mall.entity.po.MallAccount;

/**
 * @author Bu HuaYang
 */
public interface AccountService {

    MallAccount loadAccount(String accountId);

    AccountDetail loadAccountDetail(String accountId);
}
