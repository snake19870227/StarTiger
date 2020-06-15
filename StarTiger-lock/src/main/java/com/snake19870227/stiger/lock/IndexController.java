package com.snake19870227.stiger.lock;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/06/07
 */
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private final Lock lock;

    public IndexController(Lock lock) {
        this.lock = lock;
    }

    @GetMapping(path = "/getLock")
    public String getLock() {
        boolean result = lock.getLock("aaa", "bbb", 10, TimeUnit.SECONDS);
        if (result) {
            return "aaa:" + lock.get("aaa") + ":" + lock.ttl("aaa");
        } else {
            return "fail" + ":" + lock.ttl("aaa");
        }
    }
}
