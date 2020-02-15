package com.snake19870227.stiger.mall.service;

import com.snake19870227.stiger.mall.entity.po.MallGoods;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface GoodsService {

    MallGoods get(String goodsId);

    List<MallGoods> get();

    List<MallGoods> betchGet(List<String> goodsIdList);
}
