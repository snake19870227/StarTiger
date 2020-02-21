package com.snake19870227.stiger.mall.dao;

import com.snake19870227.stiger.mall.entity.po.ElasticGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bu HuaYang
 */
@Repository
public interface ElasticGoodsRepository extends ElasticsearchRepository<ElasticGoods, String> {
}
