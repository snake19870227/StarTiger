package com.snake19870227.stiger.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Bu HuaYang
 */
@Controller
public class HelloController {

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/demo1")
    public String demo1() {
        return "demo1";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }

}
