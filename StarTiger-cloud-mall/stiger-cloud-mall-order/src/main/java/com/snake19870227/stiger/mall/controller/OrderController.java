package com.snake19870227.stiger.mall.controller;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import com.snake19870227.stiger.mall.common.StarTigerMallConstant;
import com.snake19870227.stiger.mall.entity.bo.OrderDetail;
import com.snake19870227.stiger.mall.entity.dto.CreateOrderRestRequest;
import com.snake19870227.stiger.mall.entity.dto.OrderDetailRestResponse;
import com.snake19870227.stiger.mall.message.MallBusMessage;
import com.snake19870227.stiger.mall.message.MallBusSource;
import com.snake19870227.stiger.mall.message.biz.BusMsgEventOrderCreated;
import com.snake19870227.stiger.mall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_MS_PATTERN;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/order")
@EnableBinding(MallBusSource.class)
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final ObjectMapper objectMapper;

    private final MallBusSource mallBusSource;

    private final OrderService orderService;

    public OrderController(ObjectMapper objectMapper, MallBusSource mallBusSource, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.mallBusSource = mallBusSource;
        this.orderService = orderService;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @PostMapping(path = "/create")
    public OrderDetailRestResponse create(@RequestBody CreateOrderRestRequest request) throws JsonProcessingException {
        OrderDetail orderDetail = orderService.createOrder(request.getSelectGoodsList());
        MallBusMessage busMessage = new MallBusMessage();
        busMessage.setSourceService(StarTigerContext.getApplicationName())
                .setDatetime(DateUtil.format(new Date(), PURE_DATETIME_MS_PATTERN))
                .setType(StarTigerMallConstant.BusMessageBusiness.EVENT_ORDER_CREATED)
                .setBizInfo(objectMapper.writeValueAsString(new BusMsgEventOrderCreated(orderDetail.getOrderId())));
        mallBusSource.output().send(
                MessageBuilder.withPayload(busMessage).build()
        );
        return RestResponseBuilder.createSuccessRestResp(orderDetail, OrderDetailRestResponse.class);
    }
}
