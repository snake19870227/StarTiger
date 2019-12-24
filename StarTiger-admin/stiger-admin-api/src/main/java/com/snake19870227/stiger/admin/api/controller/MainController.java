package com.snake19870227.stiger.admin.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.snake19870227.stiger.admin.api.config.SecurityConfig.*;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = LOGIN_PRE_PATH)
public class MainController {

    @PostMapping(path = LOGIN_FAILURE_PATH)
    public Object loginFailure() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    @PostMapping(path = LOGIN_SUCCESS_PATH)
    public Object loginSuccess() {
        return HttpStatus.OK.value();
    }

}
