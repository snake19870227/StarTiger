package com.snake19870227.stiger.mall.message;

import cn.hutool.core.util.TypeUtil;

import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.mall.exception.BusMessageReceiverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Bu HuaYang
 */
public abstract class BaseBusMessageHandler<T> implements BusMessageHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseBusMessageHandler.class);

    @Override
    public void handler(MallBusMessage busMessage) {

        try {

            Class<?> bizInfoType = TypeUtil.getClass(TypeUtil.getTypeArgument(this.getClass()));

            T bizInfo = (T) StarTigerContext.getJsonMapper().readValue(busMessage.getBizInfo(), bizInfoType);

            doHandler(busMessage, bizInfo);

        } catch (IOException e) {
            throw new BusMessageReceiverException(e);
        }
    }

    protected abstract void doHandler(MallBusMessage busMessage, T bizInfo);
}
