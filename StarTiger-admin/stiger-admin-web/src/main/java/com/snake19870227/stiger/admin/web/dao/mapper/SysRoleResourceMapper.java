package com.snake19870227.stiger.admin.web.dao.mapper;

import com.snake19870227.stiger.admin.web.entity.po.SysRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface SysRoleResourceMapper {

    List<SysRoleResource> getAll();

    List<SysRoleResource> queryByResourceId(@Param("resId") String resId);
}
