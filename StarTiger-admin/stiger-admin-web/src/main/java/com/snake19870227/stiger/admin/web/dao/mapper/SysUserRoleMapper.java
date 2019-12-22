package com.snake19870227.stiger.admin.web.dao.mapper;

import com.snake19870227.stiger.admin.web.entity.po.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface SysUserRoleMapper {

    List<SysUserRole> queryByUserId(@Param("userId") String userId);
}
