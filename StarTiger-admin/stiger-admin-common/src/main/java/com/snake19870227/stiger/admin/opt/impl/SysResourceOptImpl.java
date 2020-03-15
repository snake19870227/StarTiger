package com.snake19870227.stiger.admin.opt.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.admin.dao.mapper.SysResourceMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysRoleMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysRoleResourceMapper;
import com.snake19870227.stiger.admin.entity.bo.ResourceInfo;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import com.snake19870227.stiger.admin.opt.SysResourceOpt;

/**
 * @author Bu HuaYang
 */
@Component
@CacheConfig(cacheNames = "SysResource")
public class SysResourceOptImpl implements SysResourceOpt {

    private final SysResourceMapper sysResourceMapper;
    private final SysRoleResourceMapper sysRoleResourceMapper;
    private final SysRoleMapper sysRoleMapper;

    public SysResourceOptImpl(SysResourceMapper sysResourceMapper, SysRoleResourceMapper sysRoleResourceMapper, SysRoleMapper sysRoleMapper) {
        this.sysResourceMapper = sysResourceMapper;
        this.sysRoleResourceMapper = sysRoleResourceMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    @Cacheable(cacheNames = "SysResource", key = "'all'")
    public List<SysResource> getAll() {
        return sysResourceMapper.selectList(null);
    }

    @Override
    @Cacheable(cacheNames = "ResourceInfo", key = "#resourceFlow")
    public ResourceInfo loadResourceInfo(String resourceFlow) {
        SysResource resource = sysResourceMapper.selectById(resourceFlow);
        List<SysRoleResource> roleResources = sysRoleResourceMapper.queryByResourceFlow(resourceFlow);
        List<SysRole> roles = roleResources.stream()
                .map(sysRoleResource -> sysRoleMapper.selectById(sysRoleResource.getRoleFlow()))
                .collect(Collectors.toList());
        return new ResourceInfo(resource, roles);
    }
}
