package com.snake19870227.stiger.admin.web.controller.sys;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.dto.SysUserSearcher;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.service.sys.SysService;
import com.snake19870227.stiger.admin.service.sys.SysUserService;
import com.snake19870227.stiger.admin.web.controller.BaseController;
import com.snake19870227.stiger.core.restful.RestResponse;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/19
 */
@Controller
@RequestMapping(path = "/sys/user")
public class SysUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    private final SysService sysService;

    private final SysUserService sysUserService;

    public SysUserController(SysService sysService, SysUserService sysUserService) {
        this.sysService = sysService;
        this.sysUserService = sysUserService;
    }

    @GetMapping(path = "/main")
    public String main(@RequestParam(name = "menuCode", required = false) String menuCode,
                       Model model, HttpServletRequest request) {

        if (StrUtil.isNotBlank(menuCode)) {
            openMenu(menuCode, request);
        }

        List<SysRole> allRoles = sysService.getAllRoles();
        model.addAttribute("allRoles", allRoles);

        return "sys/user/main";
    }

    @GetMapping(path = "/list")
    public String list(@ModelAttribute SysUserSearcher searcher,
                       @RequestParam(name = "page", defaultValue = "1") long page,
                       @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
                       Model model) {
        IPage<SysUser> users = sysUserService.searchUsers(searcher, page, pageSize);
        model.addAttribute("sysUsers", users);
        return "sys/user/list";
    }

    @GetMapping(path = "/{userFlow}/info")
    @ResponseBody
    public RestResponse.DefaultRestResponse read(@PathVariable(name = "userFlow") String userFlow) {
        UserInfo userInfo = sysUserService.loadUserInfo(userFlow);
        return RestResponseBuilder.createSuccessDefaultRestResp(userInfo);
    }

    @PostMapping
    @ResponseBody
    public RestResponse.DefaultRestResponse create(@ModelAttribute SysUser updater,
                                                   @RequestParam(name = "roleFlows") String[] roleFlows) {
        SysUser user = sysUserService.createUser(updater, roleFlows);
        return RestResponseBuilder.createSuccessDefaultRestResp(user);
    }

    @PutMapping
    @ResponseBody
    public RestResponse.DefaultRestResponse modify(@ModelAttribute SysUser updater,
                                                   @RequestParam(name = "roleFlows") String[] roleFlows) {
        SysUser user = sysUserService.modifyUser(updater, roleFlows);
        return RestResponseBuilder.createSuccessDefaultRestResp(user);
    }

    @PutMapping(path = "/{userFlow}/lock")
    @ResponseBody
    public RestResponse.DefaultRestResponse modify(@PathVariable(name = "userFlow") String userFlow,
                                                   @RequestParam(name = "unlocked") boolean unlocked) {
        if (sysUserService.changeUserLockState(userFlow, unlocked)) {
            return RestResponseBuilder.createSuccessDefaultRestResp();
        } else {
            return RestResponseBuilder.createFailureDefaultRestResp();
        }
    }

    @PutMapping(path = "/{userFlow}/resetPwd")
    @ResponseBody
    public RestResponse.DefaultRestResponse modify(@PathVariable(name = "userFlow") String userFlow) {
        if (sysUserService.resetUserPassword(userFlow)) {
            return RestResponseBuilder.createSuccessDefaultRestResp();
        } else {
            return RestResponseBuilder.createFailureDefaultRestResp();
        }
    }

    @DeleteMapping(path = "/{userFlow}")
    @ResponseBody
    public RestResponse.DefaultRestResponse delete(@PathVariable(name = "userFlow") String userFlow) {
        if (sysUserService.deleteUser(userFlow)) {
            return RestResponseBuilder.createSuccessDefaultRestResp();
        } else {
            return RestResponseBuilder.createFailureDefaultRestResp();
        }
    }
}
