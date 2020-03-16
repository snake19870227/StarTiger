package com.snake19870227.stiger.admin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.snake19870227.stiger.admin.dao.mapper.SysMenuMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysUserMapper;
import com.snake19870227.stiger.admin.entity.bo.MenuInfo;
import com.snake19870227.stiger.admin.entity.bo.ResourceInfo;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.opt.MenuInfoOpt;
import com.snake19870227.stiger.admin.opt.ResourceInfoOpt;
import com.snake19870227.stiger.admin.opt.UserInfoOpt;
import com.snake19870227.stiger.admin.service.SysService;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/16
 */
@Service
public class SysServiceImpl implements SysService {

    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);

    private final SysUserMapper sysUserMapper;

    private final SysMenuMapper sysMenuMapper;

    private final ResourceInfoOpt resourceInfoOpt;

    private final UserInfoOpt userInfoOpt;

    private final MenuInfoOpt menuInfoOpt;

    public SysServiceImpl(SysUserMapper sysUserMapper, SysMenuMapper sysMenuMapper,
                          ResourceInfoOpt resourceInfoOpt, UserInfoOpt userInfoOpt,
                          MenuInfoOpt menuInfoOpt) {
        this.sysUserMapper = sysUserMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.resourceInfoOpt = resourceInfoOpt;
        this.userInfoOpt = userInfoOpt;
        this.menuInfoOpt = menuInfoOpt;
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
