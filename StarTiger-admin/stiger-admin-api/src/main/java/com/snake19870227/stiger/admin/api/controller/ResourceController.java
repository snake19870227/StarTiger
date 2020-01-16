package com.snake19870227.stiger.admin.api.controller;

import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang
 */
@RestController
public class ResourceController {

    @GetMapping(path = "/res1")
    public RestResponse<Object> res1() {
        return RestResponse.createSuccessRestResp("res1");
    }

    @GetMapping(path = "/res2")
    public RestResponse<Object> res2() {
        return RestResponse.createSuccessRestResp("res2");
    }

    @GetMapping(path = "/resError1")
    public RestResponse<Object> resError1() {
        int i = 1 / 0;
        return null;
    }
}
