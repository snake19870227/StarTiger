package com.snake19870227.stiger.websocket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/05/10
 */
@Controller
public class StompController {

    private static final Logger logger = LoggerFactory.getLogger(StompController.class);

    private final SimpMessagingTemplate template;

    public StompController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/joinRoom")
    @SendTo("/topic/roomMessage")
    public String joinRoom(@Payload(required = false) String name) {
        if (!StringUtils.hasText(name)) {
            name = "匿名用户";
        }
        return "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + name + " 加入房间";
    }

    @PostMapping(path = "/sendRoomMessage")
    @ResponseBody
    public String sendRoomMessage(@RequestBody String message, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("name");
        if (!StringUtils.hasText(username)) {
            username = "匿名用户";
        }
        template.convertAndSend("/topic/roomMessage", "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + username + " : " + message);
        return "ok";
    }
}
