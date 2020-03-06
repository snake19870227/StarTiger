package com.snake19870227.stiger.mall.entity.dto;

import com.snake19870227.stiger.http.PageRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author Bu HuaYang
 */
@ApiModel(value="MallGoods分页查询结果对象", description="")
public class GoodsPageRestResponse extends PageRestResponse<List<MallGoods>> {
}
