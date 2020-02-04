package com.snake19870227.stiger.cloud.zipkin.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.cloud.base.stream.*;
import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @author Bu HuaYang
 */
@EnableBinding({
        Source.class,
        StarTigerCloudSink.class,
        StarTigerCloudConditionSink.class,
        StarTigerCloudRetrySink.class,
        StarTigerCloudRetryDlqSink.class
})
public class NotifyReceiver {

    private static final Logger logger = LoggerFactory.getLogger(NotifyReceiver.class);

    private final ObjectMapper objectMapper;

    public NotifyReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @StreamListener(StarTigerCloudSink.INPUT)
    @SendTo(Source.OUTPUT)
    public AckPlayload helloReceiver(HelloPlayload playload, @Header("num") String num) {
        try {
            logger.info("\n收到" + num + " Msg :\n" + objectMapper.writeValueAsString(playload));
        } catch (JsonProcessingException e) {
            logger.error("无法处理收到的消息", e);
        }
        return new AckPlayload(StarTigerContext.getApplicationName(), StarTigerContext.getActiveProfiles(), playload.getDatetime());
    }

    @StreamListener(target = StarTigerCloudConditionSink.INPUT, condition = "headers['num']==1")
    public void helloCondition(HelloPlayload playload, @Header("num") String num) {
        try {
            logger.info("\n收到 Msg " + num + " :\n" + objectMapper.writeValueAsString(playload));
        } catch (JsonProcessingException e) {
            logger.error("无法处理收到的消息", e);
        }
    }

    @StreamListener(StarTigerCloudRetrySink.INPUT)
    public void retryReceiver(HelloPlayload playload) {
        try {
            logger.info("\n收到 Retry :\n" + objectMapper.writeValueAsString(playload));
        } catch (JsonProcessingException e) {
            logger.error("无法处理收到的消息", e);
        }
        throw new RuntimeException("无法处理收到的消息");
    }

    @ServiceActivator(inputChannel = "stiger-retry-topic.zipkin211.errors")
    public void retryError(Message<?> message) {
        logger.error("无法处理收到的消息!执行回调");
    }

    @StreamListener(StarTigerCloudRetryDlqSink.INPUT)
    public void retryDlqReceiver(HelloPlayload playload) {
        try {
            logger.info("\n收到 RetryDlq :\n" + objectMapper.writeValueAsString(playload));
        } catch (JsonProcessingException e) {
            logger.error("无法处理收到的消息", e);
        }
        throw new RuntimeException("无法处理收到的消息");
    }
}
