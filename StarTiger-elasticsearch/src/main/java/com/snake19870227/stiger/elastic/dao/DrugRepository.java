package com.snake19870227.stiger.elastic.dao;

import com.snake19870227.stiger.elastic.entity.po.Drug;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bu HuaYang
 */
@Repository
public interface DrugRepository extends ElasticsearchRepository<Drug, String> {
}
