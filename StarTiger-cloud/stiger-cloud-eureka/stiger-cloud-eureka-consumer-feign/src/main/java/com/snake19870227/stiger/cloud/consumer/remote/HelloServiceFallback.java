package com.snake19870227.stiger.cloud.consumer.remote;

import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;
import com.snake19870227.stiger.core.StarTigerContext;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class HelloServiceFallback implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceFallback.class);

    @Override
    public MapRestResponse hello(String somebody) {
        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("title", StarTigerContext.getMessage("tp.consumer.0001", somebody));
        return RestResponseBuilder.createFailureRestResp(dataMap, MapRestResponse.class);
    }
}
