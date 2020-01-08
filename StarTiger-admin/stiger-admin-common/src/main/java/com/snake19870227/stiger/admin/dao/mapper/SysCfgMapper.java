package com.snake19870227.stiger.admin.dao.mapper;

import com.snake19870227.stiger.admin.entity.po.SysCfg;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * @author Bu HuaYang
 */
public interface SysCfgMapper {

    Optional<SysCfg> queryByCfgCode(@Param("cfgCode") String cfgCode);

    int insert(@Param("cfg") SysCfg cfg);

    int delByCfgCode(@Param("cfgCode") String cfgCode);
}
