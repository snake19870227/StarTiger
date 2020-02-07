package com.snake19870227.stiger.mall.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.manager.MallAccountMgr;
import com.snake19870227.stiger.mall.mapper.MallAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang
 */
@Component
public class MallAccountMgrImpl implements MallAccountMgr {

    private static final Logger logger = LoggerFactory.getLogger(MallAccountMgrImpl.class);

    private final MallAccountMapper mallAccountMapper;

    public MallAccountMgrImpl(MallAccountMapper mallAccountMapper) {
        this.mallAccountMapper = mallAccountMapper;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public MallAccount getAccountByUsername(String username) {
        QueryWrapper<MallAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("account_name", username);
        return mallAccountMapper.selectOne(wrapper);
    }
}
