package com.snake19870227.stiger.admin.entity.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2020-03-16
 */
public class SysResource implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 资源流水号
     */
    @TableId(value = "res_flow", type = IdType.ASSIGN_UUID)
    private String resFlow;

    /**
     * 资源名称
     */
    private String resName;

    /**
     * 资源路径
     */
    private String resPath;


    public String getResFlow() {
        return resFlow;
    }

    public SysResource setResFlow(String resFlow) {
        this.resFlow = resFlow;
        return this;
    }

    public String getResName() {
        return resName;
    }

    public SysResource setResName(String resName) {
        this.resName = resName;
        return this;
    }

    public String getResPath() {
        return resPath;
    }

    public SysResource setResPath(String resPath) {
        this.resPath = resPath;
        return this;
    }

    @Override
    public String toString() {
        return "SysResource{" +
        "resFlow=" + resFlow +
        ", resName=" + resName +
        ", resPath=" + resPath +
        "}";
    }
}
