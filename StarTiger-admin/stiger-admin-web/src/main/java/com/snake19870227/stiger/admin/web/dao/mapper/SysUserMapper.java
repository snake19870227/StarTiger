package com.snake19870227.stiger.admin.web.dao.mapper;

import com.snake19870227.stiger.admin.web.entity.po.SysUser;

public interface SysUserMapper {

    SysUser queryByUsername(String username);
}
