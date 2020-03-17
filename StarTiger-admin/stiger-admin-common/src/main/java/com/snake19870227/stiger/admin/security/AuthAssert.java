package com.snake19870227.stiger.admin.security;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.admin.entity.bo.ResourceInfo;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.core.StarTigerConstant;

/**
 * @author Bu HuaYang
 */
@Component
public class AuthAssert {

    private static final Logger logger = LoggerFactory.getLogger(AuthAssert.class);

    private RoleVoter roleVoter = new RoleVoter();

    private final SysService sysService;

    public AuthAssert(SysService sysService) {
        this.sysService = sysService;
    }

    public boolean canAccess(HttpServletRequest request, Authentication authentication) {

        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        User user = (User) authentication.getPrincipal();

        logger.debug("开始验证[{}]是否可访问[{}]", user.getUsername(), request.getServletPath());

        List<SysResource> allResourceList = sysService.getAllResource();

        List<SysRole> matchedRoleList = new ArrayList<>();
        allResourceList.stream()
                .filter(resource -> new AntPathRequestMatcher(resource.getResPath()).matches(request))
                .forEach(resource -> {
                    ResourceInfo resourceInfo = sysService.loadResourceInfo(resource.getResFlow());
                    if (resourceInfo.getRoles() != null) {
                        matchedRoleList.addAll(resourceInfo.getRoles());
                    }
                });

        String[] roles = matchedRoleList.stream()
                .map(sysRole -> StarTigerConstant.SPRING_SECURITY_ROLE_PREFIX + sysRole.getRoleCode())
                .toArray(String[]::new);

        int result = roleVoter.vote(authentication, null, SecurityConfig.createList(roles));

        logger.debug("验证[{}]是否可访问[{}],结果:{}", user.getUsername(), request.getServletPath(), result);

        return AccessDecisionVoter.ACCESS_GRANTED == result;
    }
}
