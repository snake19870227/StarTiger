package com.snake19870227.stiger.mall.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bu HuaYang
 */
@ApiModel(value="MallGoods分页查询结果对象", description="")
public class GoodsPageRestResponse extends GoodsListRestResponse {

    @ApiModelProperty(value = "当前页码", allowableValues = "[1, infinity]")
    private int page;

    @ApiModelProperty(value = "每页条数")
    private int pageSize;

    @ApiModelProperty(value = "查询结果总数")
    private long total;

    @ApiModelProperty(value = "总页数")
    private long totalPage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}
