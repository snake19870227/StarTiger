package com.snake19870227.stiger.admin.security;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.admin.dao.mapper.SysRoleResourceMapper;
import com.snake19870227.stiger.admin.entity.bo.ResourceInfo;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.opt.SysResourceOpt;

/**
 * @author Bu HuaYang
 */
@Component
public class AuthAssert {

    private RoleVoter roleVoter = new RoleVoter();

    private final SysResourceOpt sysResourceOpt;

    private final SysRoleResourceMapper sysRoleResourceMapper;

    public AuthAssert(SysResourceOpt sysResourceOpt,
                      SysRoleResourceMapper sysRoleResourceMapper) {
        this.sysResourceOpt = sysResourceOpt;
        this.sysRoleResourceMapper = sysRoleResourceMapper;
    }

    public boolean canAccess(HttpServletRequest request, Authentication authentication) {

        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        List<SysResource> allResourceList = sysResourceOpt.getAll();

        List<SysRole> matchedRoleList = new ArrayList<>();
        allResourceList.stream()
                .filter(resource -> new AntPathRequestMatcher(resource.getResPath()).matches(request))
                .forEach(resource -> {
                    ResourceInfo resourceInfo = sysResourceOpt.loadResourceInfo(resource.getResFlow());
                    if (resourceInfo.getRoles() != null) {
                        matchedRoleList.addAll(resourceInfo.getRoles());
                    }
                });

        String[] roles = matchedRoleList.stream()
                .map(SysRole::getRoleCode)
                .toArray(String[]::new);

        int result = roleVoter.vote(authentication, null, SecurityConfig.createList(roles));

        return AccessDecisionVoter.ACCESS_DENIED != result;
    }
}
