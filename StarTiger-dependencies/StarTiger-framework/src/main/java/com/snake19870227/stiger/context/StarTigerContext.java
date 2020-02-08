package com.snake19870227.stiger.context;

import cn.hutool.core.util.ArrayUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.utils.JsonUtil;
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
public class StarTigerContext {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerContext.class);

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

    public static String getApplicationId() {
        return applicationName + "-" + springContext.getId();
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

    public static void setApplicationName(String applicationName) {
        StarTigerContext.applicationName = applicationName;
    }

    public static void setSpringContext(ApplicationContext springContext) {
        StarTigerContext.springContext = springContext;
        StarTigerContext.activeProfiles = springContext.getEnvironment().getActiveProfiles();
        if (springContext instanceof WebApplicationContext) {
            StarTigerContext.serverProperties = springContext.getBean(ServerProperties.class);
            try {
                Optional<ServletContext> scObj = Optional.ofNullable(((WebApplicationContext) springContext).getServletContext());
                StarTigerContext.servletContext = scObj.orElseThrow((Supplier<Throwable>) () -> new NullPointerException("SpringContext 中未包含 ServletContext..."));
                StarTigerContext.servletContext.setAttribute("activeProfiles", activeProfiles);
            } catch (Throwable e) {
                logger.error("未能获取到 ServletContext ! 终止程序...");
                System.exit(1);
            }
        }
    }

    public static ObjectMapper getJsonMapper() {
        ObjectMapper objectMapper;
        try {
            objectMapper = springContext.getBean(ObjectMapper.class);
        } catch (BeansException e) {
            logger.warn("系统上下文中未找到[{}]", ObjectMapper.class.getName());
            objectMapper = JsonUtil.buildJacksonObjectMapper();
        }

        return objectMapper;
    }
}
