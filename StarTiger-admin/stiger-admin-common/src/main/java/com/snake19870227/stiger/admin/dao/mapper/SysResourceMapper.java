package com.snake19870227.stiger.admin.dao.mapper;

import com.snake19870227.stiger.admin.entity.po.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Bu HuaYang
 */
public interface SysResourceMapper {

    List<SysResource> getAll();

    Optional<SysResource> queryByResId(@Param("resId") String resId);
}
