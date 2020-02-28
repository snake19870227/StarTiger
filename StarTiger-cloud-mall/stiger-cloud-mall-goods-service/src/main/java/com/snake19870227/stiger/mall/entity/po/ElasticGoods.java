package com.snake19870227.stiger.mall.entity.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bu HuaYang
 */
@Document(indexName = ElasticGoods.GOODS_INDEX_NAME, type = "_doc", createIndex = false)
public class ElasticGoods {

    public static final String GOODS_INDEX_NAME = "mallgoods";

    @Id
    private String goodsId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String goodsName;

    @Field(type = FieldType.Keyword)
    private String goodsFactory;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String goodsContent;

    @Field(type = FieldType.Keyword)
    private List<String> goodsKeywords;

    @Field(type = FieldType.Float)
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
