package com.snake19870227.stiger.admin.web.controller.sys;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

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
import com.snake19870227.stiger.admin.entity.bo.RoleInfo;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.admin.web.controller.BaseController;
import com.snake19870227.stiger.core.restful.RestResponse;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/19
 */
@Controller
@RequestMapping(path = "/sys/role")
public class SysRoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    private final SysService sysService;

    public SysRoleController(SysService sysService) {
        this.sysService = sysService;
    }

    @GetMapping(path = "/main")
    public String main(@RequestParam(name = "menuCode", required = false) String menuCode,
                       Model model, HttpServletRequest request) {

        if (StrUtil.isNotBlank(menuCode)) {
            openMenu(menuCode, request);
        }

        return "sys/role/main";

    }

    @GetMapping(path = "/list")
    public String list(@RequestParam(name = "searchCode", required = false) String searchCode,
                       @RequestParam(name = "searchName", required = false) String searchName,
                       @RequestParam(name = "searchResName", required = false) String searchResName,
                       @RequestParam(name = "page", defaultValue = "1") long page,
                       @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
                       Model model) {
        IPage<SysRole> roles = sysService.getRoles(searchCode, searchName, searchResName, page, pageSize);
        model.addAttribute("sysRoles", roles);
        return "sys/role/list";
    }

    @GetMapping(path = "/{roleFlow}")
    @ResponseBody
    public RestResponse.DefaultRestResponse read(@PathVariable(name = "roleFlow") String roleFlow) {
        RoleInfo roleInfo = sysService.readRoleInfo(roleFlow);
        return RestResponseBuilder.createSuccessDefaultRestResp(roleInfo);
    }

    @PostMapping
    @ResponseBody
    public RestResponse.DefaultRestResponse create(@ModelAttribute SysRole role,
                                                   @RequestParam(name = "resFlows") String[] resFlows) {
        sysService.createRole(role, resFlows);
        return RestResponseBuilder.createSuccessDefaultRestResp(role);
    }

    @PutMapping
    @ResponseBody
    public RestResponse.DefaultRestResponse modify(@ModelAttribute SysRole role,
                                                   @RequestParam(name = "resFlows") String[] resFlows) {
        sysService.modifyRole(role, resFlows);
        return RestResponseBuilder.createSuccessDefaultRestResp(role);
    }

    @DeleteMapping(path = "/{roleFlow}")
    @ResponseBody
    public RestResponse.DefaultRestResponse delete(@PathVariable(name = "roleFlow") String roleFlow) {
        sysService.deleteRole(roleFlow);
        return RestResponseBuilder.createSuccessDefaultRestResp();
    }
}
