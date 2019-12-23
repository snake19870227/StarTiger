package com.snake19870227.stiger.admin.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/login")
public class MainController {

    @PostMapping(path = "/failure")
    public Object loginFailure() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    @PostMapping(path = "/success")
    public Object loginSuccess() {
        return HttpStatus.OK.value();
    }

}
