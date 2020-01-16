package com.snake19870227.stiger.admin.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.project.BaseInit;
import com.snake19870227.stiger.admin.project.SuperContext;
import com.snake19870227.stiger.admin.web.ProjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Bu HuaYang
 */
@Component
public class ProjectInit extends BaseInit {

    @Override
    protected void projectInit(ApplicationArguments args) {

    }

    @Override
    protected void afterLoadedSpringContext(ApplicationContext applicationContext) {
        ProjectContext.jacksonObjectMapper = applicationContext.getBean(ObjectMapper.class);
    }
}
