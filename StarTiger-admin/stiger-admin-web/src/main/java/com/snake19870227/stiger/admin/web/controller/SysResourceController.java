package com.snake19870227.stiger.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/17
 */
@Controller
@RequestMapping(path = "/resource")
public class SysResourceController {

    @GetMapping(path = "/main")
    public String main() {
        return "sys/resource/main";
    }
}
