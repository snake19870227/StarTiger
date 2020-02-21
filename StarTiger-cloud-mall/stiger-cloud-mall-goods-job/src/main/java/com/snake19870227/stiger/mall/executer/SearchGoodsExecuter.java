package com.snake19870227.stiger.mall.executer;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.mall.dao.ElasticGoodsRepository;
import com.snake19870227.stiger.mall.entity.po.ElasticGoods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author Bu HuaYang
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class SearchGoodsExecuter {

    private static final Logger logger = LoggerFactory.getLogger(SearchGoodsExecuter.class);

    private static final String urlPrefix = "http://yao.xywy.com";

    private static final Integer minPage = 1;

    private static final Integer maxPage = 100;

    private final ElasticGoodsRepository elasticGoodsRepository;

    public SearchGoodsExecuter(ElasticGoodsRepository elasticGoodsRepository) {
        this.elasticGoodsRepository = elasticGoodsRepository;
    }

    public void execute() {
        for (int page = minPage; page <= maxPage; page++) {
            if (!pageDrugs(page)) {
                break;
            }
        }
    }

    private boolean pageDrugs(int page) {
        try {
            Document doc = Jsoup.connect(urlPrefix + "/class/164-0-0-1-0-" + page + ".htm").get();
            Element total = doc.selectFirst("#flip > span > span.span1");
            int totalPage = Integer.parseInt(StrUtil.subBetween(total.text(), "共", "页"));
            if (page > totalPage) {
                return false;
            }
            Elements items = doc.select(".h-drugs-item");
            for (Element item : items) {
                ElasticGoods goods = new ElasticGoods();

                Element name = item.child(0).child(0);
                String href = name.attr("href");
                String id = StrUtil.replace(StrUtil.subSuf(
                        href,
                        StrUtil.lastIndexOfIgnoreCase(href, "/") + 1
                ), ".htm", "");

                goods.setGoodsId(id);
                goods.setGoodsName(name.text());

                goods.setGoodsFactory(item.child(0).child(1).text());
                try {
                    goods.setGoodsPrice(new BigDecimal(item.child(0).child(2).child(0).child(0).text()));
                } catch (Exception e) {
                    goods.setGoodsPrice(new BigDecimal(0));
                }
                goods.setGoodsContent(item.child(1).child(1).child(1).text());

                loadDetail(goods, href);

                logger.info(goods.toString());

                elasticGoodsRepository.save(goods);
            }
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    private void loadDetail(ElasticGoods elasticGoods, String detailUrl) throws IOException {
        Document doc = Jsoup.connect(urlPrefix + detailUrl).get();
        Elements dis = doc.select(".d-info-dl dl:eq(4) dd a");
        dis.forEach(element -> {
            if (elasticGoods.getGoodsKeywords() == null) {
                elasticGoods.setGoodsKeywords(new ArrayList<>(dis.size()));
            }
            elasticGoods.getGoodsKeywords().add(element.text());
        });
    }
}
