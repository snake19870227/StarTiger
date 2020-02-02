package com.snake19870227.stiger.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bu HuaYang
 */
public class ClassPathUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassPathUtil.class);

    private static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    public static List<File> getResourceFolderFiles(String patternName, boolean includeJarFiles) {
        List<File> fileList = new ArrayList<>();
        String prefix = includeJarFiles ? ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX : ResourcePatternResolver.CLASSPATH_URL_PREFIX;
        try {
            for (Resource resource : resourcePatternResolver.getResources(prefix + patternName)) {
                if (!resource.isReadable()) {
                    logger.warn("无法读取 [{}], 跳过", resource.getURI());
                    continue;
                }
                fileList.add(resource.getFile());
            }
        } catch (IOException e) {
            logger.error("无法获取 'resources' 下 [{}] 文件", patternName, e);
        }
        return fileList;
    }
}
