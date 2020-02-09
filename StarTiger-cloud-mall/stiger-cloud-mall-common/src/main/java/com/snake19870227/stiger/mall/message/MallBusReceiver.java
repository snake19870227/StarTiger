package com.snake19870227.stiger.mall.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author Bu HuaYang
 */
@EnableBinding(MallBusSink.class)
public class MallBusReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MallBusReceiver.class);

    @StreamListener(MallBusSink.INPUT)
    public void receive(MallBusMessage busMessage) {
        logger.info(busMessage.toString());
    }
}
