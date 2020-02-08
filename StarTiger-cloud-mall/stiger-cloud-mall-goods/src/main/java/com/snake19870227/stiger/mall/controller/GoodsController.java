package com.snake19870227.stiger.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.http.RestResponseBuilder;
import com.snake19870227.stiger.mall.entity.dto.BetchGetGoodsListRestResponse;
import com.snake19870227.stiger.mall.entity.dto.GoodsListRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import com.snake19870227.stiger.mall.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @GetMapping(path = "/list")
    public GoodsListRestResponse list() {
        List<MallGoods> goodsList = goodsService.get();
        return RestResponseBuilder.createSuccessRestResp(goodsList, GoodsListRestResponse.class);
    }

    @PostMapping(path = "/select")
    public BetchGetGoodsListRestResponse select(@RequestBody List<String> goodsIdList) {
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
