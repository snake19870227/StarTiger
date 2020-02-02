package com.snake19870227.stiger.cloud.zipkin.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.cloud.base.entity.dto.ListRestResponse;
import com.snake19870227.stiger.cloud.base.service.TraceAccess;
import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Bu HuaYang
 */
@Component
public class TraceAccessJob {

    private static final Logger logger = LoggerFactory.getLogger(TraceAccessJob.class);

    private final ObjectMapper objectMapper;

    private final TraceAccess traceAccess;

    public TraceAccessJob(ObjectMapper objectMapper, TraceAccess traceAccess) {
        this.objectMapper = objectMapper;
        this.traceAccess = traceAccess;
    }

    @Scheduled(cron = "* * * * * ?")
    public void doAccess() throws Throwable {
        ListRestResponse response = traceAccess.access();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("master", StarTigerContext.getApplicationName());
        resultMap.put("next", response.getData());
        logger.info(objectMapper.writeValueAsString(resultMap));
    }
}
