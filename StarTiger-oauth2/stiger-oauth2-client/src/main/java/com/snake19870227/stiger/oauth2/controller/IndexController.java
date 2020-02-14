package com.snake19870227.stiger.oauth2.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Bu HuaYang
 */
@Controller
public class IndexController {

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/user")
    public @ResponseBody
    OAuth2User user(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }
}
