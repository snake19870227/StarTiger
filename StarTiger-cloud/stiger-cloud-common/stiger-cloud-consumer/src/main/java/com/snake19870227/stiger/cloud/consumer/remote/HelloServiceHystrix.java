package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Bu HuaYang
 */
public class HelloServiceHystrix implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceHystrix.class);

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
