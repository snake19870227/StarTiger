package com.snake19870227.stiger.cloud.base.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant.StreamTopic.DefaultTopic;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudSink {

    String INPUT = DefaultTopic.INPUT;

    @Input(INPUT)
    SubscribableChannel input();
}
