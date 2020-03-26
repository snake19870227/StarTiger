package com.snake19870227.stiger.elastic.controller;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.snake19870227.stiger.web.restful.RestResponse;
import com.snake19870227.stiger.web.restful.RestResponseBuilder;
import com.snake19870227.stiger.elastic.dao.DrugRepository;

/**
 * @author Bu HuaYang
 */
@RestController
public class DrugSearchController {

    @Autowired
    private DrugRepository drugRepository;

    @GetMapping(path = "/search")
    public RestResponse.DefaultRestResponse search(@RequestBody String searchStr) {
        MultiMatchQueryBuilder builder = new MultiMatchQueryBuilder(searchStr);
//        builder.
        return RestResponseBuilder.createSuccessDefaultRestResp(null);
    }
}
