package com.snake19870227.stiger.admin.api.controller;

import com.snake19870227.stiger.web.restful.RestResponse;
import com.snake19870227.stiger.web.restful.RestResponseBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang
 */
@RestController
public class ResourceController {

    @GetMapping(path = "/res1")
    public RestResponse<Object> res1() {
        return RestResponseBuilder.createSuccessDefaultRestResp("res1");
    }

    @GetMapping(path = "/res2")
    public RestResponse<Object> res2() {
        return RestResponseBuilder.createSuccessDefaultRestResp("res2");
    }

    @PostMapping(path = "/res3")
    public RestResponse<Object> res3() {
        return RestResponseBuilder.createSuccessDefaultRestResp("res3");
    }

    @GetMapping(path = "/resError1")
    public RestResponse<Object> resError1() {
        int i = 1 / 0;
        return null;
    }
}
