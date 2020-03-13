package com.snake19870227.stiger.mall.message;

import com.snake19870227.stiger.core.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

/**
 * @author Bu HuaYang
 */
public class BusMessageHandlerFactory {

    private static final Logger logger = LoggerFactory.getLogger(BusMessageHandlerFactory.class);

    private static final String HANDLER_CLASS_NAME_SUFFIX = "Handler";

    public static BusMessageHandler<?> getBusMessageHandler(MallBusMessage busMessage) {
        BusMessageHandler<?> handler = null;
        try {
            handler = StarTigerContext.getBean(busMessage.getType() + HANDLER_CLASS_NAME_SUFFIX, BusMessageHandler.class);
        } catch (BeansException e) {
            logger.warn("未找到 {} 类型的 Handler", busMessage.getType(), e);
        }
        return handler;
    }
}
