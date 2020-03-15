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
public class SysRoleResource implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色资源信息流水号
     */
    @TableId(value = "role_res_flow", type = IdType.ASSIGN_UUID)
    private String roleResFlow;

    /**
     * 角色流水号
     */
    private String roleFlow;

    /**
     * 资源流水号
     */
    private String resFlow;


    public String getRoleResFlow() {
        return roleResFlow;
    }

    public SysRoleResource setRoleResFlow(String roleResFlow) {
        this.roleResFlow = roleResFlow;
        return this;
    }

    public String getRoleFlow() {
        return roleFlow;
    }

    public SysRoleResource setRoleFlow(String roleFlow) {
        this.roleFlow = roleFlow;
        return this;
    }

    public String getResFlow() {
        return resFlow;
    }

    public SysRoleResource setResFlow(String resFlow) {
        this.resFlow = resFlow;
        return this;
    }

    @Override
    public String toString() {
        return "SysRoleResource{" +
        "roleResFlow=" + roleResFlow +
        ", roleFlow=" + roleFlow +
        ", resFlow=" + resFlow +
        "}";
    }
}
