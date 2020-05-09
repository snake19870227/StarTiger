package com.snake19870227.stiger.websocket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/05/09
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(path = "/login")
    public String login(@RequestParam(name = "name") String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        return "ok";
    }

    @GetMapping(path = "/loginUser")
    public String loginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("name");
    }
}
