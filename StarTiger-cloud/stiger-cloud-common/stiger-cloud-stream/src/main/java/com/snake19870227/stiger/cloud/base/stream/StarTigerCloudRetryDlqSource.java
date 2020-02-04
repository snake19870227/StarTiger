package com.snake19870227.stiger.cloud.base.stream;

import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudRetryDlqSource {

    String OUTPUT = StarTigerCloudConstant.StreamTopic.RetryDlqTopic.OUTPUT;

    @Output(OUTPUT)
    MessageChannel output();
}
