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
public class SysOrg implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 机构流水号
     */
    @TableId(value = "org_flow", type = IdType.ASSIGN_UUID)
    private String orgFlow;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;


    public String getOrgFlow() {
        return orgFlow;
    }

    public SysOrg setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
        return this;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public SysOrg setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public SysOrg setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    @Override
    public String toString() {
        return "SysOrg{" +
        "orgFlow=" + orgFlow +
        ", orgCode=" + orgCode +
        ", orgName=" + orgName +
        "}";
    }
}
