package com.snake19870227.stiger.mall.entity.dto;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public class CreateOrderRestRequest {

    private List<SelectGoods> selectGoodsList;

    public static class SelectGoods {

        private String goodsId;

        private int goodsNum;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }
    }

    public List<SelectGoods> getSelectGoodsList() {
        return selectGoodsList;
    }

    public void setSelectGoodsList(List<SelectGoods> selectGoodsList) {
        this.selectGoodsList = selectGoodsList;
    }
}
