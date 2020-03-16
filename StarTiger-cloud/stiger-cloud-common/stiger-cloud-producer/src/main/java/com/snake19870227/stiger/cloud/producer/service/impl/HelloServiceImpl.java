package com.snake19870227.stiger.cloud.producer.service.impl;

import com.snake19870227.stiger.cloud.producer.service.HelloService;
import com.snake19870227.stiger.core.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Bu HuaYang
 */
@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String sayHello(String somebody) {
        logger.info("{} 到访.", somebody);
        return StarTigerContext.getMessage("tp.producer.0001", somebody);
    }
}
