package com.snake19870227.hello.docker;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/31
 */
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public static class MvcConfig implements WebMvcConfigurer {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addRedirectViewController("/", "/index.html");
        }
    }

    @RestController
    public static class IndexController {

        private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

        private String builderName = "Snake";

        private final Environment env;

        public IndexController(Environment env) {
            this.env = env;
            String envBuildName = env.getProperty("BUILDER_NAME");
            if (StrUtil.isNotBlank(envBuildName)) {
                logger.info("加载环境变量'BUILDER_NAME={}'", envBuildName);
                builderName = envBuildName;
            } else {
                logger.info("未找到环境变量'BUILDER_NAME'");
            }
        }

        @GetMapping(path = "/now")
        public Map<Object, Object> now() {
            return MapUtil.builder().put("name", builderName)
                    .put("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")))
                    .build();
        }
    }
}
