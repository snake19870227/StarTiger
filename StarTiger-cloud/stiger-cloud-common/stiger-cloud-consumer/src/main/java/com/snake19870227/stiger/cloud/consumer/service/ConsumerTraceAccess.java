package com.snake19870227.stiger.cloud.consumer.service;

import com.snake19870227.stiger.cloud.base.entity.dto.ListRestResponse;
import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;
import com.snake19870227.stiger.cloud.base.properties.StarTigerCloudProperties;
import com.snake19870227.stiger.cloud.base.service.NextAccessInvoker;
import com.snake19870227.stiger.cloud.base.service.TraceAccess;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang
 */
@Component
@ConditionalOnBean(NextAccessInvoker.class)
public class ConsumerTraceAccess implements TraceAccess {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerTraceAccess.class);

    private final StarTigerCloudProperties starTigerCloudProperties;

    private final NextAccessInvoker nextAccessInvoker;

    public ConsumerTraceAccess(StarTigerCloudProperties starTigerCloudProperties, NextAccessInvoker nextAccessInvoker) {
        this.starTigerCloudProperties = starTigerCloudProperties;
        this.nextAccessInvoker = nextAccessInvoker;
    }

    @Override
    public ListRestResponse access() {
        ListRestResponse listRestResponse = RestResponseBuilder.createSuccessRestResp(null, ListRestResponse.class);
        for (String serviceName : starTigerCloudProperties.getNextServices()) {
            MapRestResponse mapRestResponse = nextAccessInvoker.accessNextService(serviceName);
            if (mapRestResponse != null) {
                listRestResponse.addData(mapRestResponse.getData());
            }
        }
        return listRestResponse;
    }
}
