package com.snake19870227.stiger.mall.tigger;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.mall.executer.SearchGoodsExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang
 */
@Component
public class SearchGoodsTrigger {

    private static final Logger logger = LoggerFactory.getLogger(SearchGoodsTrigger.class);

    @Value("${stiger.mall.goods.job.switch:N}")
    private String jobSwitch;

    private final SearchGoodsExecuter executer;

    public SearchGoodsTrigger(SearchGoodsExecuter executer) {
        this.executer = executer;
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void trigger() {
        if (StrUtil.equals(StarTigerConstant.FLAG_N, jobSwitch)) {
            logger.warn("配置 'stiger.mall.goods.job.switch' 关闭或未配置");
            return;
        }
        executer.execute("412", 1, 84);
    }
}
