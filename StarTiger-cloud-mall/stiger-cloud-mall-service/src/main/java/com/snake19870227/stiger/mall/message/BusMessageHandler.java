package com.snake19870227.stiger.mall.message;

import com.snake19870227.stiger.context.StarTigerContext;

/**
 * @author Bu HuaYang
 */
public interface BusMessageHandler<T> {

    void handler(MallBusMessage busMessage);
}
