package com.snake19870227.stiger.admin.web.config;

import com.snake19870227.stiger.admin.project.SuperContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Bu HuaYang
 */
@Component
public class ProjectInit implements ApplicationContextAware, ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProjectInit.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("程序启动完成,开始初始化...");
        Optional<ApplicationContext> ctxObj = Optional.ofNullable(SuperContext.getSpringContext());
        ctxObj.ifPresent(ctx -> {
            StandardEnvironment env = (StandardEnvironment) ctx.getEnvironment();
            env.getSystemEnvironment().forEach((s, o) -> logger.debug("SystemEnvironment[{}={}]", s, o));
            env.getSystemProperties().forEach((s, o) -> logger.debug("SystemProperties[{}={}]", s, o));
        });
        logger.info("初始化完成");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("加载 spring context...");
        SuperContext.setSpringContext(applicationContext);
    }
}
