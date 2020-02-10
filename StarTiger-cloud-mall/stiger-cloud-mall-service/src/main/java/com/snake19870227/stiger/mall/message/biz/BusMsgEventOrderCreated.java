package com.snake19870227.stiger.mall.message.biz;

/**
 * @author Bu HuaYang
 */
public class BusMsgEventOrderCreated {

    private String orderId;

    public BusMsgEventOrderCreated() {
    }

    public BusMsgEventOrderCreated(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
