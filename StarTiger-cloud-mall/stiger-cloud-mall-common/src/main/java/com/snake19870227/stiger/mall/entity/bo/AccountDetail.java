package com.snake19870227.stiger.mall.entity.bo;

import com.snake19870227.stiger.mall.entity.po.MallAccount;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * @author Bu HuaYang
 */
public class AccountDetail extends User {

    private MallAccount account;

    public AccountDetail(MallAccount account) {
        super(account.getAccountName(), account.getAccountPassword(), AuthorityUtils.NO_AUTHORITIES);
        this.account = account;
    }

    public MallAccount getAccount() {
        return account;
    }

    public void setAccount(MallAccount account) {
        this.account = account;
    }
}
