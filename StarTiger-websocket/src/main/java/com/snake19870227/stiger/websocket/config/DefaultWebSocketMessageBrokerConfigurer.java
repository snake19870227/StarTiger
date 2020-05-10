package com.snake19870227.stiger.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/05/10
 */
@Configuration
@EnableWebSocketMessageBroker
public class DefaultWebSocketMessageBrokerConfigurer implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWebSocketMessageBrokerConfigurer.class);

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompEndpoint").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/topic", "/queue");
    }
}
