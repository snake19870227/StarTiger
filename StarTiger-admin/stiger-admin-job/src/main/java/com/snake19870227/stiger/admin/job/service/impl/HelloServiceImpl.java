package com.snake19870227.stiger.admin.job.service.impl;

import com.snake19870227.stiger.admin.job.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Bu HuaYang
 */
@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void sayHello() {
        logger.info("Hello World");
    }

    @Override
    public void timer1() {
        logger.info("Timer1 [{}]", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));
    }

    @Override
    public void timer2() throws InterruptedException {
        logger.info("Timer2 [{}]", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));
        Thread.sleep(5000L);
    }
}
