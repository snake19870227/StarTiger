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
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "role_id", type = IdType.ASSIGN_UUID)
    private String roleId;

    private String roleCode;

    private String roleName;


    public String getRoleId() {
        return roleId;
    }

    public SysRole setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public SysRole setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public SysRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "roleId=" + roleId +
        ", roleCode=" + roleCode +
        ", roleName=" + roleName +
        "}";
    }
}
