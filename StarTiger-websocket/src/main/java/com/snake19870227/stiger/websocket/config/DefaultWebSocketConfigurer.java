package com.snake19870227.stiger.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.snake19870227.stiger.websocket.common.DefaultWebSocketHandler;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/05/08
 */
@Configuration
@EnableWebSocket
public class DefaultWebSocketConfigurer implements WebSocketConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWebSocketConfigurer.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/defaultHandle")
//                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*")
        ;
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new DefaultWebSocketHandler();
    }
}
