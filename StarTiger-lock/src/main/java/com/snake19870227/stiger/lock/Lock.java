package com.snake19870227.stiger.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/06/07
 */
public interface Lock {

    boolean getLock(String lockKey, String requestId, long expire, TimeUnit timeUnit);

    boolean releaseLock(String lockKey, String value);

    String get(String lockKey);

    String ttl(String lockKey);
}
