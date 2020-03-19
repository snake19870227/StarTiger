package com.snake19870227.stiger.admin.service;

import java.util.List;
import java.util.Optional;

import com.snake19870227.stiger.admin.entity.bo.MenuInfo;
import com.snake19870227.stiger.admin.entity.bo.ResourceInfo;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysUser;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/16
 */
public interface SysService {

    /* ====================< Resource >==================== */

    List<SysResource> getAllResource();

    ResourceInfo loadResourceInfo(String resourceFlow);

    List<SysResource> getResourceByRoleCode(String roleCode);

    List<SysResource> getResources(String resName, long page, long pageSize);

    SysResource readResource(String resFlow);

    SysResource createResource(SysResource resource);

    SysResource modifyResource(SysResource resource);

    boolean deleteResource(String resFlow);

    /* ====================< Role >==================== */

    List<SysRole> getRoles(String searchCode, String searchName, String searchResName, long page, long pageSize);

    /* ====================< User >==================== */

    Optional<SysUser> getUserByUsername(String username);

    UserInfo loadUserInfo(String userFlow);

    UserInfo loadUserInfoByUsername(String username);

    /* ====================< Menu >==================== */

    SysMenu getMenuByMenuCode(String menuCode);

    List<MenuInfo> allMenuTree();
}
