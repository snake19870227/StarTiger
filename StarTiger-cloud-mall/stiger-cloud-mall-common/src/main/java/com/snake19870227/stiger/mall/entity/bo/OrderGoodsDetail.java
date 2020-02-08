package com.snake19870227.stiger.mall.entity.bo;

import com.snake19870227.stiger.mall.entity.po.MallOrderGoods;

/**
 * @author Bu HuaYang
 */
public class OrderGoodsDetail extends MallOrderGoods {

    public OrderGoodsDetail(MallOrderGoods orderGoods) {
        this.setOrderGoodsId(orderGoods.getOrderGoodsId())
                .setOrderId(orderGoods.getOrderId())
                .setGoodsId(orderGoods.getGoodsId())
                .setGoodsName(orderGoods.getGoodsName())
                .setGoodsPrice(orderGoods.getGoodsPrice())
                .setGoodsNum(orderGoods.getGoodsNum())
                .setPrice(orderGoods.getPrice());
    }
}
