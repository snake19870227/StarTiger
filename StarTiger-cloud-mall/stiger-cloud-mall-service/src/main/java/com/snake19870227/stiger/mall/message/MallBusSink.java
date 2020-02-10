package com.snake19870227.stiger.mall.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

import static com.snake19870227.stiger.mall.common.StarTigerMallConstant.BusMessageChannel.INPUT_CHANNEL_SUFFIX;
import static com.snake19870227.stiger.mall.common.StarTigerMallConstant.BusMessageChannel.MALL_BUS_CHANNEL;

/**
 * @author Bu HuaYang
 */
public interface MallBusSink {

    String INPUT = MALL_BUS_CHANNEL + INPUT_CHANNEL_SUFFIX;

    @Input(INPUT)
    SubscribableChannel input();
}
