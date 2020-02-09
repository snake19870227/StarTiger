package com.snake19870227.stiger.mall.message.handler;

import com.snake19870227.stiger.mall.message.BaseBusMessageHandler;
import com.snake19870227.stiger.mall.message.MallBusMessage;
import com.snake19870227.stiger.mall.message.biz.BusMsgEventOrderCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bu HuaYang
 */
public class EventOrderCreatedHandler extends BaseBusMessageHandler<BusMsgEventOrderCreated> {

    private static final Logger logger = LoggerFactory.getLogger(EventOrderCreatedHandler.class);

    @Override
    protected void doHandler(MallBusMessage busMessage, BusMsgEventOrderCreated bizInfo) {
        logger.info("收到订单 [{}] 已创建消息", bizInfo.getOrderId());
    }
}
