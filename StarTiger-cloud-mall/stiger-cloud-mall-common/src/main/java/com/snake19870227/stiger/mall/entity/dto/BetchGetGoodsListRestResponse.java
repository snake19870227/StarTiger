package com.snake19870227.stiger.mall.entity.dto;

import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.mall.entity.po.MallGoods;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public class BetchGetGoodsListRestResponse extends GoodsListRestResponse {

    private List<String> notFoundIdList;

    public List<String> getNotFoundIdList() {
        return notFoundIdList;
    }

    public void setNotFoundIdList(List<String> notFoundIdList) {
        this.notFoundIdList = notFoundIdList;
    }
}
