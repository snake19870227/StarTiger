package com.snake19870227.stiger.admin.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake19870227.stiger.admin.entity.po.SysUser;

import java.util.Optional;

public interface SysUserMapper extends BaseMapper<SysUser> {

    default Optional<SysUser> queryByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return Optional.ofNullable(this.selectOne(wrapper));
    }
}
