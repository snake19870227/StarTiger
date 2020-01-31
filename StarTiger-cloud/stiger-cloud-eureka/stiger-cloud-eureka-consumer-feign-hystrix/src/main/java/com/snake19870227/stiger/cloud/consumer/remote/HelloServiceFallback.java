package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.consumer.entity.dto.KeyValueRestResponse;
import com.snake19870227.stiger.context.StarTigerContext;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class HelloServiceFallback implements HelloServiceHystrix {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceFallback.class);

    @Override
    public KeyValueRestResponse hello(String somebody) {
        try {
            Map<String, String> dataMap = new HashMap<>(1);
            dataMap.put("title", StarTigerContext.getMessage("template.0001", somebody));
            return RestResponseBuilder.createFailureRestResp(dataMap, KeyValueRestResponse.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error(e.getLocalizedMessage(), e);
            return null;
        }
    }
}
