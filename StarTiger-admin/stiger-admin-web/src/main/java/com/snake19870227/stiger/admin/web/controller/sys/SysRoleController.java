package com.snake19870227.stiger.admin.web.controller.sys;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.snake19870227.stiger.admin.entity.bo.RecordPage;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.service.SysService;
import com.snake19870227.stiger.admin.web.controller.BaseController;

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
        List<SysRole> roles = sysService.getRoles(searchCode, searchName, searchResName, page, pageSize);
        if (logger.isDebugEnabled() && roles instanceof RecordPage) {
            RecordPage<SysRole> recordPage = (RecordPage<SysRole>) roles;
            logger.debug("共{}页,共{}条记录,当前第{}页", recordPage.getPages(), recordPage.getTotal(), recordPage.getCurrent());
        }
        model.addAttribute("sysRoles", roles);
        return "sys/role/list";
    }
}
