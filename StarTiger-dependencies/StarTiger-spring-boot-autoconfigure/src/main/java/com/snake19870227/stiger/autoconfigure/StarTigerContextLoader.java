package com.snake19870227.stiger.autoconfigure;

import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Bu HuaYang
 */
public class StarTigerContextLoader implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerContextLoader.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("loading SpringContext...");
        StarTigerContext.setSpringContext(applicationContext);
        logger.info("SpringContext is loaded.");
    }
}
