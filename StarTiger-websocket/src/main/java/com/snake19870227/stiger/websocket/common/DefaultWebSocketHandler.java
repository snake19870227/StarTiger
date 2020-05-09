package com.snake19870227.stiger.websocket.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/05/08
 */
public class DefaultWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWebSocketHandler.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info(message.getPayload());

        session.sendMessage(new TextMessage("收到" + message.getPayload()));
    }
}
