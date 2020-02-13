package com.snake19870227.stiger.mall.entity.bo;

import com.snake19870227.stiger.mall.entity.po.MallOrder;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public class OrderDetail extends MallOrder {

    private List<OrderGoodsDetail> goodsDetailList;

    public OrderDetail() {
    }

    public OrderDetail(MallOrder order) {
        this.setOrderId(order.getOrderId())
                .setOrderDatetime(order.getOrderDatetime())
                .setOrderPrice(order.getOrderPrice());
    }

    public List<OrderGoodsDetail> getGoodsDetailList() {
        return goodsDetailList;
    }

    public OrderDetail setGoodsDetailList(List<OrderGoodsDetail> goodsDetailList) {
        this.goodsDetailList = goodsDetailList;
        return this;
    }
}
