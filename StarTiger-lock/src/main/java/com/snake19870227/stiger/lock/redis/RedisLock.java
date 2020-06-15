package com.snake19870227.stiger.lock.redis;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import com.snake19870227.stiger.lock.Lock;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/06/07
 */
public class RedisLock implements Lock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private final StringRedisTemplate stringRedisTemplate;

    public static final String UNLOCK_LUA;

    /**
     * 释放锁脚本，原子操作
     */
    static {
        UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
                "then " +
                "    return redis.call(\"del\",KEYS[1]) " +
                "else " +
                "    return 0 " +
                "end ";
    }

    public RedisLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    /**
     * 获取分布式锁，原子操作
     *
     * @param lockKey
     * @param requestId 唯一ID, 可以使用UUID.randomUUID().toString();
     * @param expire
     * @param timeUnit
     * @return
     */
    @Override
    public boolean getLock(String lockKey, String requestId, long expire, TimeUnit timeUnit) {
        try {
            RedisCallback<Boolean> callback = (connection) -> connection.set(
                    lockKey.getBytes(StandardCharsets.UTF_8),
                    requestId.getBytes(StandardCharsets.UTF_8),
                    Expiration.seconds(timeUnit.toSeconds(expire)),
                    RedisStringCommands.SetOption.SET_IF_ABSENT
            );
            return stringRedisTemplate.execute(callback);
        } catch (Exception e) {
            logger.error("redis lock error.", e);
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param requestId 唯一ID
     * @return
     */
    @Override
    public boolean releaseLock(String lockKey, String requestId) {
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(Charset.forName("UTF-8")), requestId.getBytes(Charset.forName("UTF-8")));
        };
        return stringRedisTemplate.execute(callback);
    }

    /**
     * 获取Redis锁的value值
     *
     * @param lockKey
     * @return
     */
    @Override
    public String get(String lockKey) {
        try {
            RedisCallback<String> callback
                    = (connection) -> new String(Objects.requireNonNull(connection.get(lockKey.getBytes())), StandardCharsets.UTF_8);
            return stringRedisTemplate.execute(callback);
        } catch (Exception e) {
            logger.error("get redis occurred an exception", e);
        }
        return null;
    }

    @Override
    public String ttl(String lockKey) {
        return String.valueOf(stringRedisTemplate.getExpire(lockKey));
    }
}
