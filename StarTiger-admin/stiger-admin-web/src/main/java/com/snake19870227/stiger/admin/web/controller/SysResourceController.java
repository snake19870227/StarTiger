package com.snake19870227.stiger.admin.web.controller;

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
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.service.SysService;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/17
 */
@Controller
@RequestMapping(path = "/sys/resource")
public class SysResourceController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysResourceController.class);

    private final SysService sysService;

    public SysResourceController(SysService sysService) {
        this.sysService = sysService;
    }

    @GetMapping(path = "/main")
    public String main(@RequestParam(name = "menuCode", required = false) String menuCode,
                       Model model, HttpServletRequest request) {

        if (StrUtil.isNotBlank(menuCode)) {
            openMenu(menuCode, request);
        }

        List<SysResource> resources = sysService.getResource(null, 1, 10);

        model.addAttribute("resources", resources);

        return "sys/resource/main";
    }

    @GetMapping(path = "/list")
    public String list(@RequestParam(name = "searchName", required = false) String searchName,
                       @RequestParam(name = "page", defaultValue = "1") long page,
                       @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
                       Model model) {
        List<SysResource> resources = sysService.getResource(searchName, page, pageSize);
        model.addAttribute("sysResources", resources);
        return "sys/resource/list";
    }
}
