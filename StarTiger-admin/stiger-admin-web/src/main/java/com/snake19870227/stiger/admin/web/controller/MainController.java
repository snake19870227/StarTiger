package com.snake19870227.stiger.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import com.snake19870227.stiger.admin.web.entity.vo.Sidebar;
import com.snake19870227.stiger.admin.web.service.RouterService;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.core.restful.RestResponse;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import com.snake19870227.stiger.web.exception.MvcException;

/**
 * @author Bu HuaYang
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final RouterService routerService;

    public MainController(RouterService routerService) {
        this.routerService = routerService;
    }

    @GetMapping(path = {ProjectConstant.UrlPath.ROOT, ProjectConstant.UrlPath.INDEX})
    public String index() {
        return "redirect:" + ProjectConstant.UrlPath.LOGIN;
    }

    @GetMapping(path = ProjectConstant.UrlPath.MAIN)
    public String toMain(HttpServletRequest request) {
        Sidebar userSidebar = (Sidebar) request.getSession().getAttribute(ProjectConstant.WebAttrKey.USER_SIDEBAR);
        if (userSidebar == null) {
            throw new MvcException(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + "2010"));
        }
        userSidebar.closeAll();
        return "main";
    }

    @GetMapping(path = ProjectConstant.UrlPath.MENU_ROUTING)
    public String menuRouting(@RequestParam(name = "menuCode") String menuCode,
                              HttpServletRequest request) {
        Sidebar userSidebar = (Sidebar) request.getSession().getAttribute(ProjectConstant.WebAttrKey.USER_SIDEBAR);
        if (userSidebar == null) {
            throw new MvcException(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + "2010"));
        }
        userSidebar.open(menuCode);

        SysMenu menu = routerService.getRouterMenu(menuCode);

        return "redirect:" + menu.getMenuPath();
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
