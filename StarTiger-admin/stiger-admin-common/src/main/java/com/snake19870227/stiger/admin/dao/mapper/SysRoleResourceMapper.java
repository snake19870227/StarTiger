package com.snake19870227.stiger.admin.dao.mapper;

import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface SysRoleResourceMapper {

    List<SysRoleResource> getAll();

    List<SysRoleResource> queryByResourceId(@Param("resId") String resId);
}
