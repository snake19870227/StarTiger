package com.snake19870227.stiger.admin.api.config;

import com.snake19870227.stiger.admin.project.BaseInit;
import com.snake19870227.stiger.admin.project.SuperContext;
import com.snake19870227.stiger.admin.security.JwtSignKey;
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

import java.util.Locale;
import java.util.Optional;

/**
 * @author Bu HuaYang
 */
@Component
public class ProjectInit extends BaseInit {

    private JwtSignKey jwtSignKey;

    public ProjectInit(JwtSignKey jwtSignKey) {
        this.jwtSignKey = jwtSignKey;
    }

    @Override
    protected void projectInit(ApplicationArguments args) {
        jwtSignKey.init();
    }
}
