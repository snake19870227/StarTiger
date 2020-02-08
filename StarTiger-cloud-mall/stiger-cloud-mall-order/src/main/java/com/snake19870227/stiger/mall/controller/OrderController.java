package com.snake19870227.stiger.mall.controller;

import com.snake19870227.stiger.http.RestResponseBuilder;
import com.snake19870227.stiger.mall.entity.bo.OrderDetail;
import com.snake19870227.stiger.mall.entity.dto.CreateOrderRestRequest;
import com.snake19870227.stiger.mall.entity.dto.OrderDetailRestResponse;
import com.snake19870227.stiger.mall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @PostMapping(path = "/create")
    public OrderDetailRestResponse create(@RequestBody CreateOrderRestRequest request) {
        OrderDetail orderDetail = orderService.createOrder(request.getSelectGoodsList());
        return RestResponseBuilder.createSuccessRestResp(orderDetail, OrderDetailRestResponse.class);
    }
}
