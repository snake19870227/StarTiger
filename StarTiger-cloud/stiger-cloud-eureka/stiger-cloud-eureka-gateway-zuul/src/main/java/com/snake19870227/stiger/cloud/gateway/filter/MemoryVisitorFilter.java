package com.snake19870227.stiger.cloud.gateway.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Bu HuaYang
 */
public class MemoryVisitorFilter extends VisitorFilter {

    private static Map<String, Integer> cacheMap = new HashMap<>();

    @Override
    protected Integer getVisitTimes(String visitor) {
        Integer times = Optional.ofNullable(cacheMap.get(visitor)).orElse(0);
        cacheMap.put(visitor, ++times);
        return times;
    }
}
