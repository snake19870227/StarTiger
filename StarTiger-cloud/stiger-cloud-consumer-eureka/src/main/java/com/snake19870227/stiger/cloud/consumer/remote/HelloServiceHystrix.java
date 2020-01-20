package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@Profile("feign")
@Component
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
