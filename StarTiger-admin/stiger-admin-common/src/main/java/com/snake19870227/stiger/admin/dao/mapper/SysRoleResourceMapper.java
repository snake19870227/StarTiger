package com.snake19870227.stiger.admin.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    default List<SysRoleResource> queryByResourceId(String resId) {
        QueryWrapper<SysRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("res_id", resId);
        return this.selectList(wrapper);
    }
}
