package com.snake19870227.stiger.mall.tigger;

import com.snake19870227.stiger.mall.executer.SearchGoodsExecuter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang
 */
@Component
public class SearchGoodsTrigger {

    private final SearchGoodsExecuter executer;

    public SearchGoodsTrigger(SearchGoodsExecuter executer) {
        this.executer = executer;
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void trigger() {
        executer.execute();
    }
}
