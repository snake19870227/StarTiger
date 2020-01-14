package com.snake19870227.stiger.admin.opt.impl;

import com.snake19870227.stiger.admin.dao.mapper.SysResourceMapper;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.opt.SysResourceOpt;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bu HuaYang
 */
@Component
public class SysResourceOptImpl implements SysResourceOpt {

    private SysResourceMapper sysResourceMapper;

    public SysResourceOptImpl(SysResourceMapper sysResourceMapper) {
        this.sysResourceMapper = sysResourceMapper;
    }

    @Override
    @Cacheable(cacheNames = "SysResource")
    public List<SysResource> getAll() {
        return sysResourceMapper.selectList(null);
    }
}
