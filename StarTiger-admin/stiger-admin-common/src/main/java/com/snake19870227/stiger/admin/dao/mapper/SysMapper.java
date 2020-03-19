package com.snake19870227.stiger.admin.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.snake19870227.stiger.admin.entity.bo.RecordPage;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/16
 */
public interface SysMapper {

    List<SysResource> selectResourceByRoleFlow(@Param("roleFlow") String roleFlow);

    RecordPage<SysRole> selectRoles(RecordPage<SysRole> pager,
                                    @Param("searchCode") String searchCode,
                                    @Param("searchName") String searchName,
                                    @Param("searchResName") String searchResName);
}
