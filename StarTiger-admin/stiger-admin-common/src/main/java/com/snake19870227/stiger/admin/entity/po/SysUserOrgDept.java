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
public class SysUserOrgDept implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_org_dept_id", type = IdType.ASSIGN_UUID)
    private String userOrgDeptId;

    private String orgId;

    private String orgCode;

    private String deptId;

    private String deptCode;

    private String userId;


    public String getUserOrgDeptId() {
        return userOrgDeptId;
    }

    public SysUserOrgDept setUserOrgDeptId(String userOrgDeptId) {
        this.userOrgDeptId = userOrgDeptId;
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public SysUserOrgDept setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public SysUserOrgDept setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    public String getDeptId() {
        return deptId;
    }

    public SysUserOrgDept setDeptId(String deptId) {
        this.deptId = deptId;
        return this;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public SysUserOrgDept setDeptCode(String deptCode) {
        this.deptCode = deptCode;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public SysUserOrgDept setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "SysUserOrgDept{" +
        "userOrgDeptId=" + userOrgDeptId +
        ", orgId=" + orgId +
        ", orgCode=" + orgCode +
        ", deptId=" + deptId +
        ", deptCode=" + deptCode +
        ", userId=" + userId +
        "}";
    }
}
