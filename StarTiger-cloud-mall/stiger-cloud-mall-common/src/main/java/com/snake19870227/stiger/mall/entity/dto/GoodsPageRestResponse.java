package com.snake19870227.stiger.mall.entity.dto;

import io.swagger.annotations.ApiModel;

import java.util.List;

import com.snake19870227.stiger.web.restful.PageRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallGoods;

/**
 * @author Bu HuaYang
 */
@ApiModel(value="MallGoods分页查询结果对象", description="")
public class GoodsPageRestResponse extends PageRestResponse<List<MallGoods>> {

    public GoodsPageRestResponse(String respCode, String respMessage) {
        super(respCode, respMessage);
    }

    public GoodsPageRestResponse(String respCode, String respMessage, List<MallGoods> data) {
        super(respCode, respMessage, data);
    }
}
