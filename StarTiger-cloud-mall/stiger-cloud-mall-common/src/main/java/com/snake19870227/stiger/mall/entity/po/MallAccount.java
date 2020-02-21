package com.snake19870227.stiger.mall.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bu HuaYang
 * @since 2020-02-20
 */
@ApiModel(value="MallAccount对象", description="")
public class MallAccount implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "账户id")
    @TableId(value = "account_id", type = IdType.ASSIGN_UUID)
    private String accountId;

    @ApiModelProperty(value = "账户登录名")
    private String accountName;

    @ApiModelProperty(value = "账户登录密码")
    private String accountPassword;


    public String getAccountId() {
        return accountId;
    }

    public MallAccount setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public MallAccount setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public MallAccount setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
        return this;
    }

    @Override
    public String toString() {
        return "MallAccount{" +
        "accountId=" + accountId +
        ", accountName=" + accountName +
        ", accountPassword=" + accountPassword +
        "}";
    }
}
