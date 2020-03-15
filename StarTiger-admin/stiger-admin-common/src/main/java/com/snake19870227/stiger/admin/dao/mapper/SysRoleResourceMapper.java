package com.snake19870227.stiger.admin.dao.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;

/**
 * @author Bu HuaYang
 */
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    default List<SysRoleResource> queryByResourceFlow(String resourceFlow) {
        QueryWrapper<SysRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("res_flow", resourceFlow);
        return this.selectList(wrapper);
    }

    default List<SysRoleResource> queryByRoleFlow(String roleFlow) {
        QueryWrapper<SysRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("role_flow", roleFlow);
        return this.selectList(wrapper);
    }
}
