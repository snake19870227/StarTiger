package com.snake19870227.stiger.mall.message;

import com.snake19870227.stiger.mall.config.BusMessageHandlerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Bu HuaYang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BusMessageHandlerRegistrar.class)
public @interface BusMessageHandlerScan {

    String value() default "";
}
