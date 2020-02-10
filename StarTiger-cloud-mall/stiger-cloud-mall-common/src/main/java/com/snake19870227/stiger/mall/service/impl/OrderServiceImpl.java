package com.snake19870227.stiger.mall.service.impl;

import cn.hutool.core.util.IdUtil;
import com.snake19870227.stiger.mall.entity.bo.AccountDetail;
import com.snake19870227.stiger.mall.entity.bo.OrderDetail;
import com.snake19870227.stiger.mall.entity.bo.OrderGoodsDetail;
import com.snake19870227.stiger.mall.entity.dto.CreateOrderRestRequest;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import com.snake19870227.stiger.mall.entity.po.MallOrder;
import com.snake19870227.stiger.mall.entity.po.MallOrderGoods;
import com.snake19870227.stiger.mall.manager.CloudRpcMgr;
import com.snake19870227.stiger.mall.mapper.MallOrderGoodsMapper;
import com.snake19870227.stiger.mall.mapper.MallOrderMapper;
import com.snake19870227.stiger.mall.message.MallBusSource;
import com.snake19870227.stiger.mall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Bu HuaYang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final CloudRpcMgr cloudRpcMgr;

    private final MallOrderMapper mallOrderMapper;

    private final MallOrderGoodsMapper mallOrderGoodsMapper;

    public OrderServiceImpl(CloudRpcMgr cloudRpcMgr, MallOrderMapper mallOrderMapper, MallOrderGoodsMapper mallOrderGoodsMapper) {
        this.cloudRpcMgr = cloudRpcMgr;
        this.mallOrderMapper = mallOrderMapper;
        this.mallOrderGoodsMapper = mallOrderGoodsMapper;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public OrderDetail createOrder(List<CreateOrderRestRequest.SelectGoods> selectGoodsList) {

        AccountDetail accountDetail = (AccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<String> goodsIdList = selectGoodsList.stream()
                .map(CreateOrderRestRequest.SelectGoods::getGoodsId).collect(Collectors.toList());

        List<MallGoods> goodsList = cloudRpcMgr.getGoodsList(goodsIdList, accountDetail.getJwtToken());

        Map<String, MallGoods> goodsMap = new HashMap<>();
        goodsList.forEach(mallGoods -> goodsMap.put(mallGoods.getGoodsId(), mallGoods));

        MallOrder order = new MallOrder();
        order.setOrderId(IdUtil.simpleUUID());
        order.setOrderDatetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        BigDecimal totalPrice = new BigDecimal(0);

        List<OrderGoodsDetail> orderGoodsDetailList = selectGoodsList.stream()
                .map(selectGoods -> {
                    MallGoods goods = goodsMap.get(selectGoods.getGoodsId());
                    MallOrderGoods orderGoods = new MallOrderGoods();
                    orderGoods.setOrderId(order.getOrderId())
                            .setOrderGoodsId(IdUtil.simpleUUID())
                            .setGoodsId(goods.getGoodsId())
                            .setGoodsName(goods.getGoodsName())
                            .setGoodsPrice(goods.getGoodsPrice())
                            .setGoodsNum(selectGoods.getGoodsNum())
                            .setPrice(goods.getGoodsPrice().multiply(new BigDecimal(selectGoods.getGoodsNum())));
                    return orderGoods;
                })
                .map(OrderGoodsDetail::new).collect(Collectors.toList());

        for (MallOrderGoods orderGoods : orderGoodsDetailList) {
            mallOrderGoodsMapper.insert(orderGoods);
            totalPrice = totalPrice.add(orderGoods.getPrice());
        }

        order.setOrderPrice(totalPrice);

        mallOrderMapper.insert(order);

        OrderDetail orderDetail = new OrderDetail(order);
        orderDetail.setGoodsDetailList(orderGoodsDetailList);

        return orderDetail;
    }
}
