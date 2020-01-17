package com.snake19870227.stiger.admin.project;

import cn.hutool.core.util.ArrayUtil;
import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Bu HuaYang
 */
public class SuperContext {

    private static final Logger logger = LoggerFactory.getLogger(SuperContext.class);

    private static String[] activeProfiles;
    private static String applicationName;

    private static ApplicationContext springContext;

    private static ServletContext servletContext;

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

    public static <T> T getBean(Class<? extends T> beanClass) throws BeansException {
        return springContext.getBean(beanClass);
    }

    public static void setSpringContext(ApplicationContext springContext) {
        SuperContext.springContext = springContext;
        SuperContext.activeProfiles = springContext.getEnvironment().getActiveProfiles();
        SuperContext.applicationName = springContext.getId();
        if (springContext instanceof WebApplicationContext) {
            SuperContext.serverProperties = springContext.getBean(ServerProperties.class);
            try {
                Optional<ServletContext> scObj = Optional.ofNullable(((WebApplicationContext) springContext).getServletContext());
                SuperContext.servletContext = scObj.orElseThrow((Supplier<Throwable>) () -> new NullPointerException("SpringContext 中未包含 ServletContext..."));
                SuperContext.servletContext.setAttribute("activeProfiles", activeProfiles);
            } catch (Throwable e) {
                logger.error("未能获取到 ServletContext ! 终止程序...");
                System.exit(1);
            }
        }
        RestResponse.initDefaultMessage();
    }
}
