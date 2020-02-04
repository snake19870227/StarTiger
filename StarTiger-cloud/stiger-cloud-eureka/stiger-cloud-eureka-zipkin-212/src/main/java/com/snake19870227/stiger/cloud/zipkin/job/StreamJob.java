package com.snake19870227.stiger.cloud.zipkin.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.cloud.base.stream.StarTigerCloudSource;
import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Bu HuaYang
 */
@Component
@EnableBinding(StarTigerCloudSource.class)
public class StreamJob {

    private static final Logger logger = LoggerFactory.getLogger(StreamJob.class);

    private final ObjectMapper objectMapper;

    private final StarTigerCloudSource starTigerCloudSource;

    public StreamJob(ObjectMapper objectMapper, StarTigerCloudSource starTigerCloudSource) {
        this.objectMapper = objectMapper;
        this.starTigerCloudSource = starTigerCloudSource;
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void doAccess() throws Throwable {
        starTigerCloudSource.output().send(
                MessageBuilder.withPayload(
                        StarTigerContext.getApplicationName() + " say 'Hello'. "
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"))
                ).build()
        );
    }
}
