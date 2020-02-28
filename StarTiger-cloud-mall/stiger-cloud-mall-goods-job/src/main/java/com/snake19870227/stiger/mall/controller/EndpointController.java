package com.snake19870227.stiger.mall.controller;

import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import com.snake19870227.stiger.mall.executer.SearchGoodsExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang
 */
@RestController
public class EndpointController {

    private static final Logger logger = LoggerFactory.getLogger(EndpointController.class);

    private final SearchGoodsExecuter searchGoodsExecuter;

    public EndpointController(SearchGoodsExecuter searchGoodsExecuter) {
        this.searchGoodsExecuter = searchGoodsExecuter;
    }

    @GetMapping(path = "/fetchGoods/{typeId}/{minPage}/{maxPage}")
    public RestResponse.DefaultRestResponse fetchGoods(@PathVariable(name = "typeId") String typeId,
                                                       @PathVariable(name = "minPage") Integer minPage,
                                                       @PathVariable(name = "maxPage") Integer maxPage) {
        searchGoodsExecuter.execute(typeId, minPage, maxPage);
        return RestResponseBuilder.createSuccessRestResp();
    }
}
