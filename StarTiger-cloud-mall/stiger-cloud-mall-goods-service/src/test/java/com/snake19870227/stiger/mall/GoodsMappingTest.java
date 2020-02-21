package com.snake19870227.stiger.mall;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.mall.entity.po.ElasticGoods;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import com.snake19870227.stiger.mall.mapping.GoodsMapping;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

/**
 * @author Bu HuaYang
 */

public class GoodsMappingTest {

    @Test
    public void test() {

        MallGoods mallGoods = new MallGoods();
        mallGoods.setGoodsId(IdUtil.simpleUUID());
        mallGoods.setGoodsName("商品");
        mallGoods.setGoodsFactory("厂家");
        mallGoods.setGoodsContent("商品商品商品");
        mallGoods.setGoodsPrice(new BigDecimal("10.01"));
        mallGoods.setGoodsKeyword("关键词1,关键词2");

        GoodsMapping goodsMapping = Mappers.getMapper(GoodsMapping.class);

        ElasticGoods elasticGoods = goodsMapping.toElasticGoods(mallGoods);
        Console.log(elasticGoods.toString());

        MallGoods mallGoods1 = goodsMapping.toDatabaseGoods(elasticGoods);
        Console.log(mallGoods1.toString());

        Assert.assertEquals(mallGoods.toString(), mallGoods1.toString());
    }
}
