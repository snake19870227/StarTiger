package com.snake19870227.stiger.admin.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2020-03-16
 */
public class SysCfg implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 配置代码
     */
    @TableId(value = "cfg_code", type = IdType.ASSIGN_UUID)
    private String cfgCode;

    /**
     * 配置内容
     */
    private String cfgValue;


    public String getCfgCode() {
        return cfgCode;
    }

    public SysCfg setCfgCode(String cfgCode) {
        this.cfgCode = cfgCode;
        return this;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public SysCfg setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
        return this;
    }

    @Override
    public String toString() {
        return "SysCfg{" +
        "cfgCode=" + cfgCode +
        ", cfgValue=" + cfgValue +
        "}";
    }
}
