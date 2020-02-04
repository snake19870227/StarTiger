package com.snake19870227.stiger.cloud.base.stream;

import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant;
import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant.StreamTopic.DefaultTopic;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudRetrySource {

    String OUTPUT = StarTigerCloudConstant.StreamTopic.RetryTopic.OUTPUT;

    @Output(OUTPUT)
    MessageChannel output();
}
