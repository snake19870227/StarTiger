package com.snake19870227.stiger.cloud.base.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.snake19870227.stiger.cloud.base.StarTigerCloudConstant.StreamTopic.DefaultTopic;

/**
 * @author Bu HuaYang
 */
public interface StarTigerCloudSource {

    String OUTPUT = DefaultTopic.OUTPUT;

    @Output(OUTPUT)
    MessageChannel output();
}
