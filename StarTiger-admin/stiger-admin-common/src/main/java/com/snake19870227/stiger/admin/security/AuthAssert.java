package com.snake19870227.stiger.admin.security;

import com.snake19870227.stiger.admin.dao.mapper.SysResourceMapper;
import com.snake19870227.stiger.admin.dao.mapper.SysRoleResourceMapper;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bu HuaYang
 */
@Component
public class AuthAssert {

    private RoleVoter roleVoter = new RoleVoter();

    private SysResourceMapper sysResourceMapper;

    private SysRoleResourceMapper sysRoleResourceMapper;

    public AuthAssert(SysResourceMapper sysResourceMapper,
                      SysRoleResourceMapper sysRoleResourceMapper) {
        this.sysResourceMapper = sysResourceMapper;
        this.sysRoleResourceMapper = sysRoleResourceMapper;
    }

    public boolean canAccess(HttpServletRequest request, Authentication authentication) {

        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        List<SysResource> allResourceList = sysResourceMapper.getAll();

        List<SysRoleResource> matchedRoleResourceList = new ArrayList<>();
        allResourceList.stream()
                .filter(resource -> new AntPathRequestMatcher(resource.getResPath()).matches(request))
                .forEach(resource -> matchedRoleResourceList.addAll(sysRoleResourceMapper.queryByResourceId(resource.getResId())));

        String[] roles = matchedRoleResourceList.stream().map(SysRoleResource::getRoleCode).toArray(String[]::new);

        int result = roleVoter.vote(authentication, null, SecurityConfig.createList(roles));

        return AccessDecisionVoter.ACCESS_DENIED != result;
    }
}
