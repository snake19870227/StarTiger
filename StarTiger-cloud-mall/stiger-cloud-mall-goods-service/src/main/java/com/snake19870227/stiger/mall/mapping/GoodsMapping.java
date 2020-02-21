package com.snake19870227.stiger.mall.mapping;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.mall.entity.po.ElasticGoods;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;

/**
 * @author Bu HuaYang
 */
@Mapper(
        componentModel = "spring",
        imports = {
                StrUtil.class,
                CollUtil.class,
                Arrays.class
        }
)
public interface GoodsMapping {

    @Mapping(target = "goodsId", source = "goodsId")
    @Mapping(target = "goodsName", source = "goodsName")
    @Mapping(target = "goodsFactory", source = "goodsFactory")
    @Mapping(target = "goodsContent", source = "goodsContent")
    @Mapping(target = "goodsPrice", source = "goodsPrice")
    @Mapping(target = "goodsKeyword", expression = "java(CollUtil.join(elasticGoods.getGoodsKeywords(), \",\"))")
    MallGoods toDatabaseGoods(ElasticGoods elasticGoods);

    @Mapping(target = "goodsId", source = "goodsId")
    @Mapping(target = "goodsName", source = "goodsName")
    @Mapping(target = "goodsFactory", source = "goodsFactory")
    @Mapping(target = "goodsContent", source = "goodsContent")
    @Mapping(target = "goodsPrice", source = "goodsPrice")
    @Mapping(target = "goodsKeywords", expression = "java(Arrays.asList(StrUtil.split(mallGoods.getGoodsKeyword(), \",\")))")
    ElasticGoods toElasticGoods(MallGoods mallGoods);
}
