package com.snake19870227.stiger.mall.message;

/**
 * @author Bu HuaYang
 */
public interface BusMessageHandler<T> {

    void handler(MallBusMessage busMessage);
}
