package com.snake19870227.stiger.admin.web.controller;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.admin.project.SuperContext;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Bu HuaYang
 */
@Controller
public class LoginController {

    @ModelAttribute
    public void loginMessage(HttpServletRequest request, Model model) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (StrUtil.equals(ProjectConstant.UrlParamNames.LOGIN_ERROR, paramName)) {
                model.addAttribute(ProjectConstant.ViewAttrNames.LOGIN_ERROR_MESSAGE, SuperContext.getMessage("code.1001"));
            }
            if (StrUtil.equals(ProjectConstant.UrlParamNames.LOGIN_EXPIRE, paramName)) {
                model.addAttribute(ProjectConstant.ViewAttrNames.LOGIN_ERROR_MESSAGE, SuperContext.getMessage("code.2001"));
            }
        }
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(path = "/accessDenied")
    public String accessDenied() {
        return "403";
    }
}
