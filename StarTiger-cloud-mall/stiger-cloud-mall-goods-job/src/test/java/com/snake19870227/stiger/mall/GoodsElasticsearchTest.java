package com.snake19870227.stiger.mall;

import com.snake19870227.stiger.mall.entity.po.ElasticGoods;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Bu HuaYang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsElasticsearchTest {

    private static final Logger logger = LoggerFactory.getLogger(GoodsElasticsearchTest.class);

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    public void createOrUpdateIndex() {
        boolean isExists = elasticsearchTemplate.indexExists(ElasticGoods.GOODS_INDEX_NAME);
        logger.info(String.valueOf(isExists));
        if (!isExists) {
            elasticsearchTemplate.createIndex(ElasticGoods.class);
            elasticsearchTemplate.putMapping(ElasticGoods.class);
        }
    }

    @Test
    public void termSearch1() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        NativeSearchQuery searchQuery = new NativeSearchQuery(termQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void termSearch2() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        NativeSearchQuery searchQuery = new NativeSearchQuery(termQueryBuilder);
        searchQuery.setPageable(PageRequest.of(0, 2));
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void termSearch3() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        NativeSearchQuery searchQuery = new NativeSearchQuery(termQueryBuilder);
        searchQuery.setPageable(PageRequest.of(0, 2));
        searchQuery.setMinScore(4.6f);
        Page<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForPage(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }
}
