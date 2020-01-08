package com.snake19870227.stiger.admin.project;

import org.springframework.context.ApplicationContext;

import java.util.Locale;

/**
 * @author Bu HuaYang
 */
public class SuperContext {

    private static ApplicationContext springContext;

    public static ApplicationContext getSpringContext() {
        return springContext;
    }

    public static String getMessage(String code) {
        return springContext.getMessage(code, null, Locale.CHINA);
    }

    public static String getMessage(String code, Object... args) {
        return springContext.getMessage(code, args, Locale.CHINA);
    }

    public static void setSpringContext(ApplicationContext springContext) {
        SuperContext.springContext = springContext;
    }
}
