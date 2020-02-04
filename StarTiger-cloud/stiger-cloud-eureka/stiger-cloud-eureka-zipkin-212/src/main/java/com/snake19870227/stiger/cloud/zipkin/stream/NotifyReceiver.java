package com.snake19870227.stiger.cloud.zipkin.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.cloud.base.stream.AckPlayload;
import com.snake19870227.stiger.cloud.base.stream.HelloPlayload;
import com.snake19870227.stiger.cloud.base.stream.StarTigerCloudSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author Bu HuaYang
 */
@EnableBinding({
        Sink.class,
        StarTigerCloudSink.class
})
public class NotifyReceiver {

    private static final Logger logger = LoggerFactory.getLogger(NotifyReceiver.class);

    private final ObjectMapper objectMapper;

    public NotifyReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @StreamListener(Sink.INPUT)
    public void receiverAck(AckPlayload playload) {
        try {
            logger.info("\n收到 Ack :\n" + objectMapper.writeValueAsString(playload));
        } catch (JsonProcessingException e) {
            logger.error("无法处理收到的应答", e);
        }
    }

    @StreamListener(StarTigerCloudSink.INPUT)
    public void receiverSelf(HelloPlayload playload) {
        try {
            logger.info("\n收到 Self :\n" + objectMapper.writeValueAsString(playload));
        } catch (JsonProcessingException e) {
            logger.error("无法处理自己生产的消息", e);
        }
    }
}
