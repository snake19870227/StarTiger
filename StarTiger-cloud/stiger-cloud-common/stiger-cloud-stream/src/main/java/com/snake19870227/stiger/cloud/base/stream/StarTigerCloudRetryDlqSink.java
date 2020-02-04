package com.snake19870227.stiger.cloud.base.stream;

import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudRetryDlqSink {

    String INPUT = StarTigerCloudConstant.StreamTopic.RetryDlqTopic.INPUT;

    @Input(INPUT)
    SubscribableChannel input();
}
