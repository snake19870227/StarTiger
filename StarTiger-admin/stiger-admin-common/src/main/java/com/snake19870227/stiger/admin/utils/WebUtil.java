package com.snake19870227.stiger.admin.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author Bu HuaYang
 */
public class WebUtil {

    public static final String AJAX_HEADER_NAME = "X-Requested-With";
    public static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return StrUtil.equals(AJAX_HEADER_VALUE, ServletUtil.getHeader(request, AJAX_HEADER_NAME, StandardCharsets.UTF_8));
    }
}
