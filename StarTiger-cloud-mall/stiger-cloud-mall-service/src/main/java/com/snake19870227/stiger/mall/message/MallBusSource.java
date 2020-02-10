package com.snake19870227.stiger.mall.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import static com.snake19870227.stiger.mall.common.StarTigerMallConstant.BusMessageChannel.MALL_BUS_CHANNEL;
import static com.snake19870227.stiger.mall.common.StarTigerMallConstant.BusMessageChannel.OUTPUT_CHANNEL_SUFFIX;

/**
 * @author Bu HuaYang
 */
public interface MallBusSource {

    String OUTPUT = MALL_BUS_CHANNEL + OUTPUT_CHANNEL_SUFFIX;

    @Output(OUTPUT)
    MessageChannel output();
}
