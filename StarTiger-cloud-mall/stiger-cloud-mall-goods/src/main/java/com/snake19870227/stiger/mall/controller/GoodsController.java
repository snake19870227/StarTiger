package com.snake19870227.stiger.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.snake19870227.stiger.web.restful.RestResponseBuilder;
import com.snake19870227.stiger.mall.dao.ElasticGoodsRepository;
import com.snake19870227.stiger.mall.entity.dto.BetchGetGoodsListRestResponse;
import com.snake19870227.stiger.mall.entity.dto.GoodsListRestResponse;
import com.snake19870227.stiger.mall.entity.dto.GoodsPageRestResponse;
import com.snake19870227.stiger.mall.entity.dto.GoodsRestResponse;
import com.snake19870227.stiger.mall.entity.po.ElasticGoods;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import com.snake19870227.stiger.mall.mapping.GoodsMapping;
import com.snake19870227.stiger.mall.service.GoodsService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    private final GoodsService goodsService;

    private final ElasticGoodsRepository goodsRepository;

    private final GoodsMapping goodsMapping;

    public GoodsController(GoodsService goodsService, ElasticGoodsRepository goodsRepository, GoodsMapping goodsMapping) {
        this.goodsService = goodsService;
        this.goodsRepository = goodsRepository;
        this.goodsMapping = goodsMapping;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @GetMapping(path = "")
    public GoodsListRestResponse list() {
        List<MallGoods> goodsList = goodsService.get();
        return RestResponseBuilder.createSuccessRestResp(goodsList, GoodsListRestResponse.class);
    }

    @GetMapping(path = "/search")
    public GoodsPageRestResponse search(@RequestParam(name = "s") String searchText,
                                        @RequestParam(name = "p", defaultValue = "1") int page) {
        GoodsPageRestResponse response = RestResponseBuilder.createSuccessRestResp(null, GoodsPageRestResponse.class);
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(searchText, "goodsName", "goodsContent");
        if (page < 1) {
            page = 1;
        }
        PageRequest pageRequest = PageRequest.of(--page, 20);
        Page<ElasticGoods> goodsPage = goodsRepository.search(multiMatchQueryBuilder, pageRequest);
        response.setPage(goodsPage.getNumber());
        response.setPageSize(goodsPage.getSize());
        response.setTotal(goodsPage.getTotalElements());
        response.setTotalPage(goodsPage.getTotalPages());
        if (CollUtil.isNotEmpty(goodsPage.getContent())) {
            List<MallGoods> goodsList = goodsPage.getContent().stream().map(goodsMapping::toDatabaseGoods).collect(Collectors.toList());
            response.setData(goodsList);
        }
        return response;
    }

    @GetMapping(path = "/{goodsId}")
    public GoodsRestResponse selectByGoodsId(@PathVariable(name = "goodsId") String goodsId) {
        MallGoods goods = goodsService.get(goodsId);
        return RestResponseBuilder.createSuccessRestResp(goods, GoodsRestResponse.class);
    }

    @PostMapping(path = "")
    public BetchGetGoodsListRestResponse selectByGoodsIds(@RequestBody List<String> goodsIdList) {
        List<MallGoods> goodsList = goodsService.betchGet(goodsIdList);
        List<String> foundIdList = goodsList.stream()
                .map(MallGoods::getGoodsId).collect(Collectors.toList());
        List<String> notFoundIdList = goodsIdList.stream()
                .filter(s -> !foundIdList.contains(s)).collect(Collectors.toList());
        BetchGetGoodsListRestResponse response = RestResponseBuilder.createSuccessRestResp(goodsList, BetchGetGoodsListRestResponse.class);
        response.setNotFoundIdList(notFoundIdList);
        return response;
    }
}
