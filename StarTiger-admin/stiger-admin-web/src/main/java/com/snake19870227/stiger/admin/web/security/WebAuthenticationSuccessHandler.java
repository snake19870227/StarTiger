package com.snake19870227.stiger.admin.web.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.util.AntPathMatcher;
import com.snake19870227.stiger.admin.entity.bo.MenuInfo;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import com.snake19870227.stiger.admin.web.entity.vo.Sidebar;
import com.snake19870227.stiger.core.StarTigerConstant;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/16
 */
public class WebAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebAuthenticationSuccessHandler.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    private final SysService sysService;

    public WebAuthenticationSuccessHandler(SysService sysService) {
        this.sysService = sysService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        loadUserSidebar(request, authentication);

        saveRequest(request, response);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if (!StrUtil.equals(request.getServletPath(), ProjectConstant.UrlPath.LOGIN)) {
            requestCache.saveRequest(request, response);
        }
    }

    private void loadUserSidebar(HttpServletRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<MenuInfo> menuInfos = sysService.allMenuTree();
        List<MenuInfo> userMenuInfos = menuInfos.stream().filter(
                menuInfo -> {
                    List<SysMenu> childMenus = menuInfo.getChildMenus().stream().filter(
                            sysMenu -> {
                                for (GrantedAuthority authority : user.getAuthorities()) {
                                    String roleCode = StrUtil.replace(authority.getAuthority(), StarTigerConstant.SPRING_SECURITY_ROLE_PREFIX, "");
                                    List<SysResource> roleResources = sysService.getResourceByRoleCode(roleCode);
                                    if (CollUtil.isNotEmpty(roleResources)) {
                                        for (SysResource resource : roleResources) {
                                            AntPathMatcher matcher = new AntPathMatcher();
                                            if (matcher.match(resource.getResPath(), sysMenu.getMenuPath())) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                                return false;
                            }
                    ).collect(Collectors.toList());
                    return CollUtil.isNotEmpty(childMenus);
                }
        ).collect(Collectors.toList());
        request.getSession().setAttribute(ProjectConstant.WebAttrKey.USER_SIDEBAR, new Sidebar(userMenuInfos));
    }
}