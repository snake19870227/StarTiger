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
public class SysOrg implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "org_id", type = IdType.ASSIGN_UUID)
    private String orgId;

    private String orgCode;

    private String orgName;


    public String getOrgId() {
        return orgId;
    }

    public SysOrg setOrgId(String orgId) {
        this.orgId = orgId;
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
        "orgId=" + orgId +
        ", orgCode=" + orgCode +
        ", orgName=" + orgName +
        "}";
    }
}
