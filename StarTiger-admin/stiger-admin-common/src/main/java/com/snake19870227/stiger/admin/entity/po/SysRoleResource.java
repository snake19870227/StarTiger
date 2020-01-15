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
public class SysRoleResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "role_res_id", type = IdType.ASSIGN_UUID)
    private String roleResId;

    private String roleId;

    private String roleCode;

    private String resId;

    private String resCode;


    public String getRoleResId() {
        return roleResId;
    }

    public SysRoleResource setRoleResId(String roleResId) {
        this.roleResId = roleResId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public SysRoleResource setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public SysRoleResource setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public String getResId() {
        return resId;
    }

    public SysRoleResource setResId(String resId) {
        this.resId = resId;
        return this;
    }

    public String getResCode() {
        return resCode;
    }

    public SysRoleResource setResCode(String resCode) {
        this.resCode = resCode;
        return this;
    }

    @Override
    public String toString() {
        return "SysRoleResource{" +
        "roleResId=" + roleResId +
        ", roleId=" + roleId +
        ", roleCode=" + roleCode +
        ", resId=" + resId +
        ", resCode=" + resCode +
        "}";
    }
}
