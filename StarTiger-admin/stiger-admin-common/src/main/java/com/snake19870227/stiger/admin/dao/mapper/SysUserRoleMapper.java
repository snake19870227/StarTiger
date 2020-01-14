package com.snake19870227.stiger.admin.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake19870227.stiger.admin.entity.po.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    default List<SysUserRole> queryByUserId(String userId) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.selectList(wrapper);
    }
}
