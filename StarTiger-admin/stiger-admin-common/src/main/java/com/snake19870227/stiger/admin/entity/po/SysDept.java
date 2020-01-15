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
public class SysDept implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "dept_id", type = IdType.ASSIGN_UUID)
    private String deptId;

    private String orgId;

    private String orgCode;

    private String deptCode;

    private String deptName;


    public String getDeptId() {
        return deptId;
    }

    public SysDept setDeptId(String deptId) {
        this.deptId = deptId;
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public SysDept setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public SysDept setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public SysDept setDeptCode(String deptCode) {
        this.deptCode = deptCode;
        return this;
    }

    public String getDeptName() {
        return deptName;
    }

    public SysDept setDeptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    @Override
    public String toString() {
        return "SysDept{" +
        "deptId=" + deptId +
        ", orgId=" + orgId +
        ", orgCode=" + orgCode +
        ", deptCode=" + deptCode +
        ", deptName=" + deptName +
        "}";
    }
}
