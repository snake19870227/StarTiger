package com.snake19870227.stiger.cloud.zipkin.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author Bu HuaYang
 */
@EnableBinding({
        Sink.class
})
public class NotifyReceiver {

    private static final Logger logger = LoggerFactory.getLogger(NotifyReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receiverAck(Object playload) {
        logger.info("收到应答 : " + playload);
    }
}
