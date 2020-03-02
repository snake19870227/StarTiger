package com.snake19870227.stiger.mall.entity.po;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class ElasticGoodsInfo extends ElasticGoods {

    private Float score;

    private Map<String, HighlightField> highlightFieldMap;

    @Override
    public String toString() {
        return "ElasticGoodsInfo{" +
                super.toString() +
                ",score=" + score +
                ", highlightFieldMap=" + highlightFieldMap +
                '}';
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Map<String, HighlightField> getHighlightFieldMap() {
        return highlightFieldMap;
    }

    public void setHighlightFieldMap(Map<String, HighlightField> highlightFieldMap) {
        this.highlightFieldMap = highlightFieldMap;
    }
}
