package com.snake19870227.stiger.admin.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.snake19870227.stiger.admin.entity.dto.SysResModel;
import com.snake19870227.stiger.admin.entity.dto.SysRoleModel;
import com.snake19870227.stiger.admin.entity.dto.SysUserModel;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysUser;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/24
 */
@Mapper(componentModel = "spring")
public interface SysObjectMapStruct {

    @Mapping(target = "resFlow", source = "resFlow")
    @Mapping(target = "resName", source = "resName")
    @Mapping(target = "resPath", source = "resPath")
    SysResource toResourcePo(SysResModel model);

    @Mapping(target = "roleFlow", source = "roleFlow")
    @Mapping(target = "roleCode", source = "roleCode")
    @Mapping(target = "roleName", source = "roleName")
    SysRole toRolePo(SysRoleModel model);

    @Mapping(target = "userFlow", source = "userFlow")
    @Mapping(target = "shortName", source = "shortName")
    SysUser toUserPo(SysUserModel model);
}
