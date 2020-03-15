package com.snake19870227.stiger.admin.opt;

import com.snake19870227.stiger.admin.entity.bo.UserInfo;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/16
 */
public interface UserInfoOpt {

    UserInfo loadUserInfo(String userFlow);
}
