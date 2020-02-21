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
 * @since 2020-02-20
 */
@ApiModel(value="MallOrderGoods对象", description="")
public class MallOrderGoods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单商品id")
    @TableId(value = "order_goods_id", type = IdType.ASSIGN_UUID)
    private String orderGoodsId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "订单商品数量")
    private Integer goodsNum;

    @ApiModelProperty(value = "总价")
    private BigDecimal price;


    public String getOrderGoodsId() {
        return orderGoodsId;
    }

    public MallOrderGoods setOrderGoodsId(String orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public MallOrderGoods setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public MallOrderGoods setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public MallOrderGoods setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public MallOrderGoods setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
        return this;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public MallOrderGoods setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MallOrderGoods setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "MallOrderGoods{" +
        "orderGoodsId=" + orderGoodsId +
        ", orderId=" + orderId +
        ", goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsPrice=" + goodsPrice +
        ", goodsNum=" + goodsNum +
        ", price=" + price +
        "}";
    }
}
