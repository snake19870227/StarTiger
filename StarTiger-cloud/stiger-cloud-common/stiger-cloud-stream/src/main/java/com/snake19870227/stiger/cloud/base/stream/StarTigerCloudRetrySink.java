package com.snake19870227.stiger.cloud.base.stream;

import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant;
import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant.StreamTopic.DefaultTopic;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudRetrySink {

    String INPUT = StarTigerCloudConstant.StreamTopic.RetryTopic.INPUT;

    @Input(INPUT)
    SubscribableChannel input();
}
