package com.snake19870227.stiger.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import com.snake19870227.stiger.core.restful.RestResponse;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;

/**
 * @author Bu HuaYang
 */
@Controller
public class MainController {

    @GetMapping(path = {ProjectConstant.UrlPath.ROOT, ProjectConstant.UrlPath.INDEX})
    public String index() {
        return "redirect:" + ProjectConstant.UrlPath.LOGIN;
    }

    @GetMapping(path = ProjectConstant.UrlPath.MAIN)
    public String toMain() {
        return "main";
    }

    @PostMapping(path = "/sayHello")
    public String sayHello(@RequestParam(name = "name") String name,
                           Model model) {
        model.addAttribute("sayHello", "hello, " + name);
        return "main1";
    }

    @GetMapping(path = "/res1")
    @ResponseBody
    public RestResponse<Object> res1() {
        return RestResponseBuilder.createSuccessDefaultRestResp("res1");
    }

    @GetMapping(path = "/res2")
    @ResponseBody
    public RestResponse<Object> res2() {
        return RestResponseBuilder.createSuccessDefaultRestResp("res2");
    }

    @GetMapping(path = "/res3")
    public String res3() {
        return "demo/res3";
    }

    @GetMapping(path = "/resError1")
    public String resError1() {
        int i = 1 / 0;
        return null;
    }
}
