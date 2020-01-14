package com.snake19870227.stiger.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author Bu HuaYang
 */
public class SysCfg {

    @TableId
    private String cfgCode;
    private String cfgValue;

    public String getCfgCode() {
        return cfgCode;
    }

    public void setCfgCode(String cfgCode) {
        this.cfgCode = cfgCode;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }
}
