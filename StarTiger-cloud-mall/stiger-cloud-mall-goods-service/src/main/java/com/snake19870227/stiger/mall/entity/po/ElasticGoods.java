package com.snake19870227.stiger.mall.entity.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bu HuaYang
 */
@Document(indexName = "mallgoods", type = "_doc")
public class ElasticGoods {

    @Id
    private String goodsId;

    private String goodsName;

    private String goodsFactory;

    private String goodsContent;

    private List<String> goodsKeywords;

    private BigDecimal goodsPrice;

    @Override
    public String toString() {
        return "ElasticGoods{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsFactory='" + goodsFactory + '\'' +
                ", goodsContent='" + goodsContent + '\'' +
                ", goodsKeywords=" + goodsKeywords +
                ", goodsPrice=" + goodsPrice +
                '}';
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsFactory() {
        return goodsFactory;
    }

    public void setGoodsFactory(String goodsFactory) {
        this.goodsFactory = goodsFactory;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public List<String> getGoodsKeywords() {
        return goodsKeywords;
    }

    public void setGoodsKeywords(List<String> goodsKeywords) {
        this.goodsKeywords = goodsKeywords;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
