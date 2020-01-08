package com.snake19870227.stiger.admin.dao.mapper;

import com.snake19870227.stiger.admin.entity.po.SysUser;

import java.util.Optional;

public interface SysUserMapper {

    Optional<SysUser> queryByUsername(String username);
}
