package com.snake19870227.stiger.mall.service;

import com.snake19870227.stiger.mall.entity.bo.OrderDetail;
import com.snake19870227.stiger.mall.entity.dto.CreateOrderRestRequest;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public interface OrderService {

    OrderDetail createOrder(List<CreateOrderRestRequest.SelectGoods> selectGoodsList);
}
