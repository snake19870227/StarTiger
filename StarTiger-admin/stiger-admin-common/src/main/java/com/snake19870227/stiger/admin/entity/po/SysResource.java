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
 * @since 2020-01-15
 */
public class SysResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "res_id", type = IdType.ASSIGN_UUID)
    private String resId;

    private String resCode;

    private String resName;

    private String resPath;


    public String getResId() {
        return resId;
    }

    public SysResource setResId(String resId) {
        this.resId = resId;
        return this;
    }

    public String getResCode() {
        return resCode;
    }

    public SysResource setResCode(String resCode) {
        this.resCode = resCode;
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
        "resId=" + resId +
        ", resCode=" + resCode +
        ", resName=" + resName +
        ", resPath=" + resPath +
        "}";
    }
}
