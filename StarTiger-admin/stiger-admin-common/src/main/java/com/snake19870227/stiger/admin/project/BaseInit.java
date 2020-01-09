package com.snake19870227.stiger.admin.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

import java.util.Optional;

/**
 * @author Bu HuaYang
 */
public abstract class BaseInit implements ApplicationContextAware, ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(BaseInit.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("程序[{}]{}启动完成,开始初始化...", SuperContext.getApplicationName(), SuperContext.getActiveProfiles());
        projectInit(args);
        logger.info("初始化完成");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        logger.info("加载 spring context...");

        SuperContext.setSpringContext(applicationContext);

        if (logger.isDebugEnabled()) {
            Optional<Environment> envObj = Optional.of(SuperContext.getEnvironment());
            envObj.ifPresent(env -> {
                StandardEnvironment standardEnvironment = (StandardEnvironment) env;
                standardEnvironment.getSystemEnvironment().forEach((s, o) -> logger.debug("SystemEnvironment[{}={}]", s, o));
                standardEnvironment.getSystemProperties().forEach((s, o) -> logger.debug("SystemProperties[{}={}]", s, o));
            });
        }

        logger.info("加载 spring context 完成");
    }

    protected abstract void projectInit(ApplicationArguments args);
}
