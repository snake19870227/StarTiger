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
public class SysUserRole implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户角色信息流水号
     */
    @TableId(value = "user_role_flow", type = IdType.ASSIGN_UUID)
    private String userRoleFlow;

    /**
     * 用户流水号
     */
    private String userFlow;

    /**
     * 角色流水号
     */
    private String roleFlow;


    public String getUserRoleFlow() {
        return userRoleFlow;
    }

    public SysUserRole setUserRoleFlow(String userRoleFlow) {
        this.userRoleFlow = userRoleFlow;
        return this;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public SysUserRole setUserFlow(String userFlow) {
        this.userFlow = userFlow;
        return this;
    }

    public String getRoleFlow() {
        return roleFlow;
    }

    public SysUserRole setRoleFlow(String roleFlow) {
        this.roleFlow = roleFlow;
        return this;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
        "userRoleFlow=" + userRoleFlow +
        ", userFlow=" + userFlow +
        ", roleFlow=" + roleFlow +
        "}";
    }
}
