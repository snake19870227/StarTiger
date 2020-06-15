package com.snake19870227.stiger.lock;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.snake19870227.stiger.lock.redis.RedisLock;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/06/07
 */
@Configuration
public class Config {

    @Bean
    @Primary
    @ConditionalOnClass(RedisTemplate.class)
    public Lock redisLock(StringRedisTemplate redisTemplate) {
        return new RedisLock(redisTemplate);
    }
}
