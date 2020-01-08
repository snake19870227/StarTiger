package com.snake19870227.stiger.admin.security;

import java.security.Key;

/**
 * @author Bu HuaYang
 */
public interface JwtSignKey {

    void init();

    Key getSignKey();

    Key getSigningKey();
}
