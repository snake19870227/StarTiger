package com.snake19870227.stiger.autoconfigure;

import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * @author Bu HuaYang
 */
public class StarTigerContextLoader implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerContextLoader.class);

    @Value("${spring.application.name:}")
    private String applicationName;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("loading SpringContext...");
        if (StringUtils.isEmpty(applicationName)) {
            logger.error("Property 'spring.application.name' is empty.");
            System.exit(0);
        }
        StarTigerContext.setApplicationName(applicationName);
        StarTigerContext.setSpringContext(applicationContext);
        logger.info("SpringContext is loaded.");
    }
}
