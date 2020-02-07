package com.snake19870227.stiger.mall.service.impl;

import com.snake19870227.stiger.mall.entity.bo.AccountDetail;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.mapper.MallAccountMapper;
import com.snake19870227.stiger.mall.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Bu HuaYang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final MallAccountMapper mallAccountMapper;

    public AccountServiceImpl(MallAccountMapper mallAccountMapper) {
        this.mallAccountMapper = mallAccountMapper;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public MallAccount loadAccount(String accountId) {
        return mallAccountMapper.selectById(accountId);
    }

    @Override
    public AccountDetail loadAccountDetail(String accountId) {
        Optional<MallAccount> account = Optional.ofNullable(mallAccountMapper.selectById(accountId));
        return account.map(AccountDetail::new).orElse(null);
    }
}
