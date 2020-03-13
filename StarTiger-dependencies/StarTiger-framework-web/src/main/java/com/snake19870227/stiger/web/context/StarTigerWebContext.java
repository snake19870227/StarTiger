package com.snake19870227.stiger.web.context;

import javax.servlet.ServletContext;
import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import com.snake19870227.stiger.core.StarTigerContext;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/13
 */
public class StarTigerWebContext {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerWebContext.class);

    private static ServletContext servletContext;

    private static ServerProperties serverProperties;

    public static void setSpringContext(ApplicationContext springContext) {
        serverProperties = springContext.getBean(ServerProperties.class);
        try {
            Optional<ServletContext> scObj = Optional.ofNullable(((WebApplicationContext) springContext).getServletContext());
            servletContext = scObj.orElseThrow((Supplier<Throwable>) () -> new NullPointerException("SpringContext 中未包含 ServletContext..."));
            servletContext.setAttribute("activeProfiles", StarTigerContext.getActiveProfiles());
        } catch (Throwable e) {
            logger.error("未能获取到 ServletContext ! 终止程序...");
            System.exit(1);
        }
    }
}
