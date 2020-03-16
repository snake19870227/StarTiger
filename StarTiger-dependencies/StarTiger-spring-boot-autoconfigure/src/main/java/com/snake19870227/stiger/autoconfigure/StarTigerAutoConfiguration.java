package com.snake19870227.stiger.autoconfigure;

import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import com.snake19870227.stiger.web.context.StarTigerWebContextLoader;
import com.snake19870227.stiger.core.context.StarTigerContextLoader;
import com.snake19870227.stiger.core.utils.ClassPathUtil;

/**
 * @author Bu HuaYang
 */
@Configuration
public class StarTigerAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerAutoConfiguration.class);

    @Bean
    public StarTigerContextLoader starTigerContextLoader() {
        return new StarTigerContextLoader();
    }

    @Bean
    @ConditionalOnWebApplication
    public StarTigerWebContextLoader starTigerWebContextLoader() {
        return new StarTigerWebContextLoader();
    }

    @Bean
    public MessageSource messageSource() {
        String suffix = "_" + Locale.CHINA + ".properties";
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        String propertyFileNamePattern = "*" + suffix;
        List<File> fileList = ClassPathUtil.getResourceFolderFiles(propertyFileNamePattern, true);
        if (fileList.isEmpty()) {
            messageSource.setBasenames("base-messages");
        } else {
            String[] basenames = fileList.stream().map(file -> {
                String fileName = file.getName();
                logger.info("加载[{}]", fileName);
                return StrUtil.subBefore(fileName, suffix, true);
            }).toArray(String[]::new);
            messageSource.setBasenames(basenames);
        }
        return messageSource;
    }
}
