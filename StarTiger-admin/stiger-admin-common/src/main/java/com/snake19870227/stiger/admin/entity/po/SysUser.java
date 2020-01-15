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
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    private String username;

    private String encodePassword;


    public String getUserId() {
        return userId;
    }

    public SysUser setUserId(String userId) {
        this.userId = userId;
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
        "userId=" + userId +
        ", username=" + username +
        ", encodePassword=" + encodePassword +
        "}";
    }
}
