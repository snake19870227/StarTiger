package com.snake19870227.stiger.cloud.base.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import static com.snake19870227.stiger.cloud.base.StarTigerCloudConstant.StreamTopic.DEFAULT_TOPIC;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudSource {

    String OUTPUT = DEFAULT_TOPIC;

    @Output(OUTPUT)
    MessageChannel output();
}
