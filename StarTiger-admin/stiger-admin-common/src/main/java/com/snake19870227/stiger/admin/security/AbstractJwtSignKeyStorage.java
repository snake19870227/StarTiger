package com.snake19870227.stiger.admin.security;

import com.snake19870227.stiger.admin.dao.mapper.SysCfgMapper;
import com.snake19870227.stiger.admin.entity.po.SysCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;

import java.time.Duration;

/**
 * @author Bu HuaYang
 */
public abstract class AbstractJwtSignKeyStorage {

    @Autowired
    private SysCfgMapper sysCfgMapper;

    protected String getKey(String code) {

        SysCfg sysCfg = sysCfgMapper.queryByCfgCode(code);

        return ObjectUtils.isEmpty(sysCfg) ? null : sysCfg.getCfgValue();
    }

    protected void saveKey(String code, String key) {

        SysCfg cfg = new SysCfg();

        cfg.setCfgCode(code);
        cfg.setCfgValue(key);

        sysCfgMapper.insert(cfg);
    }

    protected void delKey(String code) {

        sysCfgMapper.delByCfgCode(code);
    }
}
