package com.snake19870227.stiger.admin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake19870227.stiger.admin.dao.mapper.SysMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysMenuMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysResourceMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysRoleMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysRoleResourceMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysUserMapper;
import com.snake19870227.stiger.admin.entity.bo.MenuInfo;
import com.snake19870227.stiger.admin.entity.bo.RecordPage;
import com.snake19870227.stiger.admin.entity.bo.ResourceInfo;
import com.snake19870227.stiger.admin.entity.bo.RoleInfo;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.opt.MenuInfoOpt;
import com.snake19870227.stiger.admin.opt.ResourceInfoOpt;
import com.snake19870227.stiger.admin.opt.RoleInfoOpt;
import com.snake19870227.stiger.admin.opt.UserInfoOpt;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.core.exception.ServiceException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/16
 */
@Service
public class SysServiceImpl implements SysService {

    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);

    private final SysUserMapper sysUserMapper;

    private final SysResourceMapper sysResourceMapper;

    private final SysRoleMapper sysRoleMapper;

    private final SysRoleResourceMapper sysRoleResourceMapper;

    private final SysMenuMapper sysMenuMapper;

    private final SysMapper sysMapper;

    private final ResourceInfoOpt resourceInfoOpt;

    private final UserInfoOpt userInfoOpt;

    private final MenuInfoOpt menuInfoOpt;

    private final RoleInfoOpt roleInfoOpt;

    public SysServiceImpl(SysUserMapper sysUserMapper, SysResourceMapper sysResourceMapper,
                          SysRoleMapper sysRoleMapper, SysRoleResourceMapper sysRoleResourceMapper,
                          SysMenuMapper sysMenuMapper,
                          SysMapper sysMapper, ResourceInfoOpt resourceInfoOpt,
                          UserInfoOpt userInfoOpt, MenuInfoOpt menuInfoOpt, RoleInfoOpt roleInfoOpt) {
        this.sysUserMapper = sysUserMapper;
        this.sysResourceMapper = sysResourceMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysRoleResourceMapper = sysRoleResourceMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.sysMapper = sysMapper;
        this.resourceInfoOpt = resourceInfoOpt;
        this.userInfoOpt = userInfoOpt;
        this.menuInfoOpt = menuInfoOpt;
        this.roleInfoOpt = roleInfoOpt;
    }

    /* ====================< Resource >==================== */

    @Override
    public List<SysResource> getAllResource() {
        return resourceInfoOpt.getAll();
    }

    @Override
    public ResourceInfo loadResourceInfo(String resourceFlow) {
        return resourceInfoOpt.loadResourceInfo(resourceFlow);
    }

    @Override
    public List<SysResource> getResourceByRoleCode(String roleCode) {
        return resourceInfoOpt.getByRoleCode(roleCode);
    }

    @Override
    public RecordPage<SysResource> getResources(String resName, long page, long pageSize) {
        RecordPage<SysResource> pager = new RecordPage<>(page, pageSize);
        return sysResourceMapper.get(pager, resName);
    }

    @Override
    @Cacheable(cacheNames = "SysResource", key = "#resFlow")
    public SysResource readResource(String resFlow) {
        return sysResourceMapper.selectById(resFlow);
    }

    @Override
    @Caching(
            evict = @CacheEvict(cacheNames = "SysResource", key = "'all'", beforeInvocation = true),
            put = @CachePut(cacheNames = "SysResource", key = "#resource.resFlow")
    )
    @Transactional(rollbackFor = Exception.class)
    public SysResource createResource(SysResource resource) {
        if (sysResourceMapper.insert(resource) != 1) {
            throw new ServiceException("新增资源失败");
        }
        return resource;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "SysResource", key = "'all'", beforeInvocation = true),
                    @CacheEvict(cacheNames = "ResourceInfo", key = "#resource.resFlow", beforeInvocation = true)
            },
            put = @CachePut(cacheNames = "SysResource", key = "#resource.resFlow")
    )
    @Transactional(rollbackFor = Exception.class)
    public SysResource modifyResource(SysResource resource) {
        if (sysResourceMapper.updateById(resource) != 1) {
            throw new ServiceException("修改资源失败");
        }
        return resource;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "SysResource", key = "'all'", beforeInvocation = true),
                    @CacheEvict(cacheNames = "SysResource", key = "#resFlow", beforeInvocation = true),
                    @CacheEvict(cacheNames = "ResourceInfo", key = "#resFlow", beforeInvocation = true),
                    @CacheEvict(cacheNames = "RoleInfo", allEntries = true, beforeInvocation = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteResource(String resFlow) {
        sysRoleResourceMapper.deleteByResFlow(resFlow);
        return sysResourceMapper.deleteById(resFlow) == 1;
    }

    /* ====================< Role >==================== */

    @Override
    public IPage<SysRole> getRoles(String searchCode, String searchName, String searchResName, long page, long pageSize) {
        RecordPage<SysRole> pager = new RecordPage<>(page, pageSize);
        return sysMapper.selectRoles(pager, searchCode, searchName, searchResName);
    }

    @Override
    public RoleInfo readRoleInfo(String roleFlow) {
        return roleInfoOpt.readRoleInfo(roleFlow);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "SysRole", key = "'all'", beforeInvocation = true),
                    @CacheEvict(cacheNames = "SysRole", key = "#roleFlow", beforeInvocation = true),
                    @CacheEvict(cacheNames = "RoleInfo", key = "#roleFlow", beforeInvocation = true),
                    @CacheEvict(cacheNames = "ResourceInfo", allEntries = true, beforeInvocation = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(String roleFlow) {
        sysRoleResourceMapper.deleteByRoleFlow(roleFlow);
        return sysRoleMapper.deleteById(roleFlow) == 1;
    }

    private void createRoleResources(SysRole role, String[] resFlows) {
        for (String resFlow : resFlows) {
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleFlow(role.getRoleFlow())
                    .setResFlow(resFlow);
            sysRoleResourceMapper.insert(roleResource);
        }
    }

    @Override
    @Caching(
            put = @CachePut(cacheNames = "SysRole", key = "#role.roleFlow")
    )
    @Transactional(rollbackFor = Exception.class)
    public SysRole createRole(SysRole role, String[] resFlows) {
        sysRoleMapper.insert(role);
        createRoleResources(role, resFlows);
        return role;
    }

    @Override
    @Caching(
            evict = @CacheEvict(cacheNames = "RoleInfo", key = "#role.roleFlow", beforeInvocation = true),
            put = @CachePut(cacheNames = "SysRole", key = "#role.roleFlow")
    )
    @Transactional(rollbackFor = Exception.class)
    public SysRole modifyRole(SysRole role, String[] resFlows) {
        sysRoleMapper.updateById(role);
        sysRoleResourceMapper.deleteByRoleFlow(role.getRoleFlow());
        createRoleResources(role, resFlows);
        return role;
    }

    /* ====================< User >==================== */

    @Override
    public Optional<SysUser> getUserByUsername(String username) {
        return sysUserMapper.queryByUsername(username);
    }

    @Override
    public UserInfo loadUserInfo(String userFlow) {
        return userInfoOpt.loadUserInfo(userFlow);
    }

    @Override
    public UserInfo loadUserInfoByUsername(String username) {
        Optional<SysUser> userObj = getUserByUsername(username);
        return userObj.map(userInfoOpt::loadUserInfo).orElse(null);
    }

    /* ====================< Menu >==================== */

    @Override
    public SysMenu getMenuByMenuCode(String menuCode) {
        return sysMenuMapper.getMenuByMenuCode(menuCode);
    }

    @Override
    public List<MenuInfo> allMenuTree() {
        List<SysMenu> level1Menus = menuInfoOpt.getAllLevel1Menu();
        return level1Menus.stream().map(menuInfoOpt::menuTree).collect(Collectors.toList());
    }
}
