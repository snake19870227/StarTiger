package com.snake19870227.stiger.mall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.mall.entity.po.ElasticGoods;
import com.snake19870227.stiger.mall.entity.po.ElasticGoodsInfo;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
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
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private ObjectMapper objectMapper;

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
    public void search1() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        NativeSearchQuery searchQuery = new NativeSearchQuery(termQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search2() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        NativeSearchQuery searchQuery = new NativeSearchQuery(termQueryBuilder);
        searchQuery.setPageable(PageRequest.of(0, 2));
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search3() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        NativeSearchQuery searchQuery = new NativeSearchQuery(termQueryBuilder);
        searchQuery.setPageable(PageRequest.of(0, 2));
        searchQuery.setMinScore(4.6f);
        Page<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForPage(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search4() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        HighlightBuilder.Field goodsNameField = new HighlightBuilder.Field("goodsName");
        NativeSearchQuery searchQuery
                = new NativeSearchQuery(termQueryBuilder, null, null, new HighlightBuilder.Field[] {goodsNameField});
        List<ElasticGoodsInfo> elasticGoodsList
                = elasticsearchTemplate.query(searchQuery, response -> {
                    List<ElasticGoodsInfo> elasticGoodsList1 = new ArrayList<>();
                    for (SearchHit searchHit : response.getHits()) {
                        Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
                        try {
                            ElasticGoodsInfo elasticGoods = objectMapper.readValue(searchHit.getSourceAsString(), ElasticGoodsInfo.class);
                            elasticGoods.setHighlightFieldMap(highlightFieldMap);
                            elasticGoods.setScore(searchHit.getScore());
                            elasticGoodsList1.add(elasticGoods);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return elasticGoodsList1;
                });
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.toString()));
    }

    @Test
    public void search5() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("goodsName", "滴");
        HighlightBuilder.Field goodsNameField = new HighlightBuilder.Field("goodsName");
        NativeSearchQuery searchQuery
                = new NativeSearchQuery(termQueryBuilder, null, null, new HighlightBuilder.Field[] {goodsNameField});
        searchQuery.setMinScore(5.0f);
        List<ElasticGoodsInfo> elasticGoodsList
                = elasticsearchTemplate.query(searchQuery, response -> {
            List<ElasticGoodsInfo> elasticGoodsList1 = new ArrayList<>();
            for (SearchHit searchHit : response.getHits()) {
                Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
                try {
                    ElasticGoodsInfo elasticGoods = objectMapper.readValue(searchHit.getSourceAsString(), ElasticGoodsInfo.class);
                    elasticGoods.setHighlightFieldMap(highlightFieldMap);
                    elasticGoods.setScore(searchHit.getScore());
                    elasticGoodsList1.add(elasticGoods);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            return elasticGoodsList1;
        });
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.toString()));
    }

    @Test
    public void search6() {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("goodsName", "感冒发烧");
        NativeSearchQuery searchQuery = new NativeSearchQuery(matchQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search7() {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("感冒发烧", "goodsName", "goodsContent");
        NativeSearchQuery searchQuery = new NativeSearchQuery(multiMatchQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search8() {
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("goodsName", "滴", "颗粒");
        NativeSearchQuery searchQuery = new NativeSearchQuery(termsQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search9() {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("goodsPrice");
        rangeQueryBuilder.gte(10f).lte(20f);
        NativeSearchQuery searchQuery = new NativeSearchQuery(rangeQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search10() {
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("goodsName", "*佛山*");
        NativeSearchQuery searchQuery = new NativeSearchQuery(wildcardQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search11() {
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery();
        idsQueryBuilder.addIds("6691", "6299");
        NativeSearchQuery searchQuery = new NativeSearchQuery(idsQueryBuilder);
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info(elasticGoods.getGoodsName()));
    }

    @Test
    public void search12() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("goodsName", "风湿");
        MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("goodsContent", "风湿");
        matchQueryBuilder2.boost(2);
        boolQueryBuilder.should(matchQueryBuilder1).should(matchQueryBuilder2);
        NativeSearchQuery searchQuery = new NativeSearchQuery(boolQueryBuilder);
        searchQuery.setPageable(PageRequest.of(0, 20));
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info("{} - {}", elasticGoods.getGoodsName(), elasticGoods.getGoodsContent()));
    }

    @Test
    public void search13() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("goodsName", "止咳");
        MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("goodsContent", "止咳");
        boolQueryBuilder.must(matchQueryBuilder1).must(matchQueryBuilder2);
        NativeSearchQuery searchQuery = new NativeSearchQuery(boolQueryBuilder);
        searchQuery.setPageable(PageRequest.of(0, 20));
        List<ElasticGoods> elasticGoodsList = elasticsearchTemplate.queryForList(searchQuery, ElasticGoods.class);
        elasticGoodsList.forEach(elasticGoods -> logger.info("{} - {}", elasticGoods.getGoodsName(), elasticGoods.getGoodsContent()));
    }
}
