package com.snake19870227.stiger.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.snake19870227.stiger.admin.web.ProjectConstant;
import com.snake19870227.stiger.admin.web.entity.vo.Sidebar;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.web.exception.MvcException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/17
 */
public class BaseController {

    protected void openMenu(String menuCode, HttpServletRequest request) {
        getUserSidebar(request).open(menuCode);
    }

    protected void closeAllMenu(HttpServletRequest request) {
        getUserSidebar(request).closeAll();
    }

    protected Sidebar getUserSidebar(HttpServletRequest request) {
        Sidebar userSidebar = (Sidebar) request.getSession().getAttribute(ProjectConstant.WebAttrKey.USER_SIDEBAR);
        if (userSidebar == null) {
            throw new MvcException(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + "2010"));
        }
        return userSidebar;
    }
}
