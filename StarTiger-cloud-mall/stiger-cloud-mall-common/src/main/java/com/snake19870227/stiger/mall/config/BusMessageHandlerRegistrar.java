package com.snake19870227.stiger.mall.config;

import cn.hutool.core.util.ReflectUtil;
import com.snake19870227.stiger.mall.message.BusMessageHandler;
import com.snake19870227.stiger.mall.message.BusMessageHandlerScan;
import com.snake19870227.stiger.utils.ClassPathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author Bu HuaYang
 */
public class BusMessageHandlerRegistrar implements ImportBeanDefinitionRegistrar {

    private static final Logger logger = LoggerFactory.getLogger(BusMessageHandlerRegistrar.class);

    private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(BusMessageHandlerScan.class.getName()));
        if (mapperScanAttrs != null) {
            String basePackage = mapperScanAttrs.getString("value");
            logger.info("开始扫描 BusMessageHandler [{}] ...", basePackage);
            List<Class<?>> handlerClassList = ClassPathUtil.scanPackageClass(basePackage);
            for (Class<?> handlerClass : handlerClassList) {
                logger.debug("扫描得到 {}", handlerClass.getName());
                if (Modifier.isAbstract(handlerClass.getModifiers())) {
                    continue;
                }
                Class<?>[] ifaces = ClassUtils.getAllInterfacesForClass(handlerClass);
                for (Class<?> iface : ifaces) {
                    if (iface == BusMessageHandler.class) {
                        BeanDefinition busMsgHandlerBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(handlerClass).getBeanDefinition();
                        String beanName = this.beanNameGenerator.generateBeanName(busMsgHandlerBeanDefinition, registry);
                        logger.debug("注入Bean [{}]", beanName);
                        registry.registerBeanDefinition(beanName, busMsgHandlerBeanDefinition);
                        break;
                    }
                }
            }
        }
    }
}
