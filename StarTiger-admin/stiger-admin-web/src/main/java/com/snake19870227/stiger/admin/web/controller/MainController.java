package com.snake19870227.stiger.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Bu HuaYang
 */
@Controller
public class MainController {

    @GetMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(path = "/main")
    public String toMain() {
        return "main";
    }

    @PostMapping(path = "/sayHello")
    public String sayHello(@RequestParam(name = "name") String name,
                           Model model) {
        model.addAttribute("sayHello", "hello, " + name);
        return "main";
    }

    @GetMapping(path = "/res1")
    @ResponseBody
    public String res1() {
        return "res1";
    }

    @GetMapping(path = "/res2")
    @ResponseBody
    public String res2() {
        return "res2";
    }
}
