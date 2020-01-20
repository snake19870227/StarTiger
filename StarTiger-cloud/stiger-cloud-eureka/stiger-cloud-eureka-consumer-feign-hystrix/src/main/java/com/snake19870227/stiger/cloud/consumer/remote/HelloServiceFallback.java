package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Bu HuaYang
 */
public class HelloServiceFallback implements HelloServiceHystrix {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceFallback.class);

    @Override
    public KeyValueRestResponse hello() {
        try {
            return RestResponseBuilder.createFailureRestResp(null, KeyValueRestResponse.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error(e.getLocalizedMessage(), e);
            return null;
        }
    }
}
