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
public class SysUserSubject implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户隶属信息流水号
     */
    @TableId(value = "subject_flow", type = IdType.ASSIGN_UUID)
    private String subjectFlow;

    /**
     * 机构流水号
     */
    private String orgFlow;

    /**
     * 部门流水号
     */
    private String deptFlow;

    /**
     * 用户流水号
     */
    private String userFlow;


    public String getSubjectFlow() {
        return subjectFlow;
    }

    public SysUserSubject setSubjectFlow(String subjectFlow) {
        this.subjectFlow = subjectFlow;
        return this;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public SysUserSubject setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
        return this;
    }

    public String getDeptFlow() {
        return deptFlow;
    }

    public SysUserSubject setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
        return this;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public SysUserSubject setUserFlow(String userFlow) {
        this.userFlow = userFlow;
        return this;
    }

    @Override
    public String toString() {
        return "SysUserSubject{" +
        "subjectFlow=" + subjectFlow +
        ", orgFlow=" + orgFlow +
        ", deptFlow=" + deptFlow +
        ", userFlow=" + userFlow +
        "}";
    }
}
