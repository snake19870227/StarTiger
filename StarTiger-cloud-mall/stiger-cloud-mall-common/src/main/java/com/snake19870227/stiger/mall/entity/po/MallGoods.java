package com.snake19870227.stiger.mall.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bu HuaYang
 * @since 2020-02-06
 */
public class MallGoods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    @TableId(value = "goods_id", type = IdType.ASSIGN_UUID)
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;


    public String getGoodsId() {
        return goodsId;
    }

    public MallGoods setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public MallGoods setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public MallGoods setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
        return this;
    }

    @Override
    public String toString() {
        return "MallGoods{" +
        "goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsPrice=" + goodsPrice +
        "}";
    }
}
