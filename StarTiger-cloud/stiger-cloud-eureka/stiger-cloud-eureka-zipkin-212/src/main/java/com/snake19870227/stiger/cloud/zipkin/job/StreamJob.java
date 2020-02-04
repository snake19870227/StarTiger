package com.snake19870227.stiger.cloud.zipkin.job;

import cn.hutool.core.util.RandomUtil;
import com.snake19870227.stiger.cloud.base.stream.HelloPlayload;
import com.snake19870227.stiger.cloud.base.stream.StarTigerCloudRetryDlqSource;
import com.snake19870227.stiger.cloud.base.stream.StarTigerCloudRetrySource;
import com.snake19870227.stiger.cloud.base.stream.StarTigerCloudSource;
import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Bu HuaYang
 */
@Component
@EnableBinding({
        StarTigerCloudSource.class,
        StarTigerCloudRetrySource.class,
        StarTigerCloudRetryDlqSource.class
})
public class StreamJob {

    private static final Logger logger = LoggerFactory.getLogger(StreamJob.class);

    private final StarTigerCloudSource starTigerCloudSource;

    private final StarTigerCloudRetrySource starTigerCloudRetrySource;

    private final StarTigerCloudRetryDlqSource starTigerCloudRetryDlqSource;

    public StreamJob(StarTigerCloudSource starTigerCloudSource,
                     StarTigerCloudRetrySource starTigerCloudRetrySource,
                     StarTigerCloudRetryDlqSource starTigerCloudRetryDlqSource) {
        this.starTigerCloudSource = starTigerCloudSource;
        this.starTigerCloudRetrySource = starTigerCloudRetrySource;
        this.starTigerCloudRetryDlqSource = starTigerCloudRetryDlqSource;
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void doAccess() {
        int num = RandomUtil.randomInt(0, 2);
        starTigerCloudSource.output().send(
                MessageBuilder.withPayload(
                        new HelloPlayload(
                                StarTigerContext.getApplicationName() + "." + StarTigerCloudSource.OUTPUT,
                                StarTigerContext.getActiveProfiles(),
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"))
                        )
                ).setHeader("num", num).build()
        );
    }

    @Scheduled(cron = "0 * * * * ?")
    public void doRetryAccess() {
        starTigerCloudRetrySource.output().send(
                MessageBuilder.withPayload(
                        new HelloPlayload(
                                StarTigerContext.getApplicationName() + "." + StarTigerCloudRetrySource.OUTPUT,
                                StarTigerContext.getActiveProfiles(),
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"))
                        )
                ).build()
        );
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void doRetryDlqAccess() {
        starTigerCloudRetryDlqSource.output().send(
                MessageBuilder.withPayload(
                        new HelloPlayload(
                                StarTigerContext.getApplicationName() + "." + StarTigerCloudRetryDlqSource.OUTPUT,
                                StarTigerContext.getActiveProfiles(),
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"))
                        )
                ).build()
        );
    }
}
