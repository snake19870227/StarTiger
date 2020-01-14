package com.snake19870227.stiger.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author Bu HuaYang
 */
public class SysRole {

    @TableId
    private String roleId;
    private String roleCode;
    private String roleName;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
