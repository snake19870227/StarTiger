package com.snake19870227.stiger.mall.controller;

import com.snake19870227.stiger.mall.entity.dto.GoodsListRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @GetMapping(path = "/list")
    public GoodsListRestResponse list() {
        return null;
    }
}
