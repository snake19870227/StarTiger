package com.snake19870227.stiger.cloud.zipkin.stream;

import com.snake19870227.stiger.cloud.base.stream.StarTigerCloudSink;
import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Arrays;

/**
 * @author Bu HuaYang
 */
@EnableBinding({
        Source.class,
        StarTigerCloudSink.class
})
public class NotifyReceiver {

    private static final Logger logger = LoggerFactory.getLogger(NotifyReceiver.class);

    @StreamListener(StarTigerCloudSink.INPUT)
    @SendTo(Source.OUTPUT)
    public String customReceiver1(Object playload) {
        logger.info("收到 : " + playload);
        return StarTigerContext.getApplicationName() + Arrays.toString(StarTigerContext.getActiveProfiles()) + " Ack";
    }
}
