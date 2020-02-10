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

    public MallBusReceiver() {
        logger.debug("创建 {}", this.getClass().getName());
    }

    @StreamListener(MallBusSink.INPUT)
    public void receive(MallBusMessage busMessage) {
        logger.info("收到总线消息\n{}", busMessage.toString());
        BusMessageHandler<?> handler = BusMessageHandlerFactory.getBusMessageHandler(busMessage);
        if (handler != null) {
            handler.handler(busMessage);
        }
    }
}
