package com.snake19870227.stiger.cloud.base.service;

import com.snake19870227.stiger.cloud.base.entity.dto.MapRestResponse;

/**
 * @author Bu HuaYang
 */
public interface HelloService {

    MapRestResponse hello();

    MapRestResponse helloFallback();
}
