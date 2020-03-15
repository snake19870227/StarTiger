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
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户流水号
     */
    @TableId(value = "user_flow", type = IdType.ASSIGN_UUID)
    private String userFlow;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户登录密码
     */
    private String encodePassword;


    public String getUserFlow() {
        return userFlow;
    }

    public SysUser setUserFlow(String userFlow) {
        this.userFlow = userFlow;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SysUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEncodePassword() {
        return encodePassword;
    }

    public SysUser setEncodePassword(String encodePassword) {
        this.encodePassword = encodePassword;
        return this;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "userFlow=" + userFlow +
        ", username=" + username +
        ", encodePassword=" + encodePassword +
        "}";
    }
}
