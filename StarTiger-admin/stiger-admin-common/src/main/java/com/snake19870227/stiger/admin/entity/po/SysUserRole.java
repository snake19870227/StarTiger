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
public class SysUserRole implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_role_id", type = IdType.ASSIGN_UUID)
    private String userRoleId;

    private String userId;

    private String roleId;

    private String roleCode;


    public String getUserRoleId() {
        return userRoleId;
    }

    public SysUserRole setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public SysUserRole setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public SysUserRole setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public SysUserRole setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
        "userRoleId=" + userRoleId +
        ", userId=" + userId +
        ", roleId=" + roleId +
        ", roleCode=" + roleCode +
        "}";
    }
}
