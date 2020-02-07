package com.snake19870227.stiger.mall.controller;

import com.snake19870227.stiger.http.RestResponseBuilder;
import com.snake19870227.stiger.mall.entity.dto.GoodsListRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import com.snake19870227.stiger.mall.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
