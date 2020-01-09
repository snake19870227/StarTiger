package com.snake19870227.stiger.admin.project;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Locale;

/**
 * @author Bu HuaYang
 */
public class SuperContext {

    private static String[] activeProfiles;
    private static String applicationName;

    private static ApplicationContext springContext;

    private static ServerProperties serverProperties;

    private static ApplicationContext getSpringContext() {
        return springContext;
    }

    public static Environment getEnvironment() {
        return getSpringContext().getEnvironment();
    }

    public static String getApplicationName() {
        return applicationName;
    }

    public static String[] getActiveProfiles() {
        return activeProfiles;
    }

    public static boolean isProfileActived(String... profileNames) {
        if (ArrayUtil.isEmpty(activeProfiles)) {
            return false;
        }
        return ArrayUtil.containsAny(activeProfiles, profileNames);
    }

    public static String getMessage(String code) {
        return springContext.getMessage(code, null, Locale.CHINA);
    }

    public static String getMessage(String code, Object... args) {
        return springContext.getMessage(code, args, Locale.CHINA);
    }

    public static void setSpringContext(ApplicationContext springContext) {
        SuperContext.springContext = springContext;
        SuperContext.activeProfiles = springContext.getEnvironment().getActiveProfiles();
        SuperContext.applicationName = springContext.getApplicationName();
        SuperContext.serverProperties = springContext.getBean(ServerProperties.class);
    }
}
