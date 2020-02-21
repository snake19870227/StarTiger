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
@ApiModel(value="MallGoods对象", description="")
public class MallGoods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "goods_id", type = IdType.ASSIGN_UUID)
    private String goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品生产厂家")
    private String goodsFactory;

    @ApiModelProperty(value = "商品说明")
    private String goodsContent;

    @ApiModelProperty(value = "商品标签")
    private String goodsKeyword;

    @ApiModelProperty(value = "商品价格")
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

    public String getGoodsFactory() {
        return goodsFactory;
    }

    public MallGoods setGoodsFactory(String goodsFactory) {
        this.goodsFactory = goodsFactory;
        return this;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public MallGoods setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
        return this;
    }

    public String getGoodsKeyword() {
        return goodsKeyword;
    }

    public MallGoods setGoodsKeyword(String goodsKeyword) {
        this.goodsKeyword = goodsKeyword;
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
        ", goodsFactory=" + goodsFactory +
        ", goodsContent=" + goodsContent +
        ", goodsKeyword=" + goodsKeyword +
        ", goodsPrice=" + goodsPrice +
        "}";
    }
}
