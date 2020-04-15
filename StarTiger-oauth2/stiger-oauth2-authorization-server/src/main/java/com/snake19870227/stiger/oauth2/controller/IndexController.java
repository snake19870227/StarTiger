package com.snake19870227.stiger.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/14
 */
@RestController
public class IndexController {

    @GetMapping(path = "/callback")
    public String callback(@RequestParam String code,
                           @RequestParam(required = false) String state) {
        return code;
    }

//    @GetMapping(path = "/user")
//    public Principal user(Principal user,
//                          HttpServletRequest request) {
//        return user;
//    }
}
