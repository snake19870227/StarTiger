package com.snake19870227.stiger.cloud.base.stream;

import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant.StreamTopic.DefaultConditionTopic;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudConditionSink {

    String INPUT = DefaultConditionTopic.INPUT;

    @Input(INPUT)
    SubscribableChannel input();
}
