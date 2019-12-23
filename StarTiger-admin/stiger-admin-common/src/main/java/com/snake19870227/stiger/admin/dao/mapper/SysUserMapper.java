package com.snake19870227.stiger.admin.dao.mapper;

import com.snake19870227.stiger.admin.entity.po.SysUser;

public interface SysUserMapper {

    SysUser queryByUsername(String username);
}
