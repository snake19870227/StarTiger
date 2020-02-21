package com.snake19870227.stiger.admin.job.tigger;

import com.snake19870227.stiger.admin.job.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang
 */
@Component
public class TimerTrigger {

    private static final Logger logger = LoggerFactory.getLogger(TimerTrigger.class);

    @Autowired
    private HelloService helloService;

    @Scheduled(cron = "* * * * * ?")
    public void timer1Invoke() {
        helloService.timer1();
    }

    @Scheduled(cron = "* * * * * ?")
    public void timer2Invoke() {
        try {
            helloService.timer2();
        } catch (InterruptedException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }
}
