package com.snake19870227.stiger.admin.security;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake19870227.stiger.admin.dao.mapper.SysCfgMapper;
import com.snake19870227.stiger.admin.entity.po.SysCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Bu HuaYang
 */
public abstract class AbstractJwtSignKeyStorage {

    @Autowired
    private SysCfgMapper sysCfgMapper;

    protected String getKey(String code) {
        Optional<SysCfg> sysCfgObj = Optional.ofNullable(sysCfgMapper.selectById(code));
        return sysCfgObj.map(SysCfg::getCfgValue).orElse(null);
    }

    protected void saveKey(String code, String key) {
        SysCfg cfg = new SysCfg();
        cfg.setCfgCode(code);
        cfg.setCfgValue(key);
        sysCfgMapper.insert(cfg);
    }

    protected void delKey(String code) {
        sysCfgMapper.deleteById(code);
    }
}
