package com.snake19870227.stiger.admin.api.security;

import java.security.Key;

/**
 * @author Bu HuaYang
 */
public interface JwtSignKey {

    Key getSignKey();

    Key getSigningKey();
}
