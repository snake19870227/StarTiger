package com.snake19870227.stiger.cloud.base.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

import static com.snake19870227.stiger.cloud.base.StarTigerCloudConstant.StreamTopic.DEFAULT_TOPIC;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudSink {

    String INPUT = DEFAULT_TOPIC;

    @Input(INPUT)
    SubscribableChannel input();
}
