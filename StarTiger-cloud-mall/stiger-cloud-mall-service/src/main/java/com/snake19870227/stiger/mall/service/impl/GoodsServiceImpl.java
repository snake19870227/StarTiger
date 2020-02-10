package com.snake19870227.stiger.mall.service.impl;

import com.snake19870227.stiger.mall.entity.po.MallGoods;
import com.snake19870227.stiger.mall.mapper.MallGoodsMapper;
import com.snake19870227.stiger.mall.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bu HuaYang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    private final MallGoodsMapper mallGoodsMapper;

    public GoodsServiceImpl(MallGoodsMapper mallGoodsMapper) {
        this.mallGoodsMapper = mallGoodsMapper;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public List<MallGoods> get() {
        return mallGoodsMapper.selectList(null);
    }

    @Override
    public List<MallGoods> betchGet(List<String> goodsIdList) {
        return mallGoodsMapper.selectBatchIds(goodsIdList);
    }
}
