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
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色流水号
     */
    @TableId(value = "role_flow", type = IdType.ASSIGN_UUID)
    private String roleFlow;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;


    public String getRoleFlow() {
        return roleFlow;
    }

    public SysRole setRoleFlow(String roleFlow) {
        this.roleFlow = roleFlow;
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
        "roleFlow=" + roleFlow +
        ", roleCode=" + roleCode +
        ", roleName=" + roleName +
        "}";
    }
}
