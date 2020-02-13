package com.snake19870227.stiger.mall.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bu HuaYang
 * @since 2020-02-13
 */
@ApiModel(value="MallOrder对象", description="")
public class MallOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "order_id", type = IdType.ASSIGN_UUID)
    private String orderId;

    @ApiModelProperty(value = "下单订单时间")
    private String orderDatetime;

    @ApiModelProperty(value = "订单总价")
    private BigDecimal orderPrice;


    public String getOrderId() {
        return orderId;
    }

    public MallOrder setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderDatetime() {
        return orderDatetime;
    }

    public MallOrder setOrderDatetime(String orderDatetime) {
        this.orderDatetime = orderDatetime;
        return this;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public MallOrder setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    @Override
    public String toString() {
        return "MallOrder{" +
        "orderId=" + orderId +
        ", orderDatetime=" + orderDatetime +
        ", orderPrice=" + orderPrice +
        "}";
    }
}
