package com.snake19870227.stiger.admin.web.controller;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import com.snake19870227.stiger.core.StarTigerContext;

/**
 * @author Bu HuaYang
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ModelAttribute
    public void loginMessage(HttpServletRequest request, Model model) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (StrUtil.equals(ProjectConstant.UrlParamNames.LOGIN_ERROR, paramName)) {
                model.addAttribute(ProjectConstant.ViewAttrNames.LOGIN_ERROR_MESSAGE, StarTigerContext.getMessage("code.1001"));
            }
            if (StrUtil.equals(ProjectConstant.UrlParamNames.LOGIN_EXPIRE, paramName)) {
                model.addAttribute(ProjectConstant.ViewAttrNames.LOGIN_ERROR_MESSAGE, StarTigerContext.getMessage("code.2003"));
            }
        }
    }

    @GetMapping(path = ProjectConstant.UrlPath.LOGIN)
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            if (user != null) {
                logger.info("用户[{}]已登录", user.getUsername());
                return "redirect:" + ProjectConstant.UrlPath.MAIN;
            }
        }
        return "login";
    }

    @GetMapping(path = ProjectConstant.UrlPath.ACCESS_DENIED)
    public String accessDenied(HttpServletResponse response) {
        return "403";
    }
}
