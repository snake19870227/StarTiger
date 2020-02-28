package com.snake19870227.stiger.elastic;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.elastic.dao.DrugRepository;
import com.snake19870227.stiger.elastic.entity.po.Drug;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author Bu HuaYang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugMain {

    private static final Logger logger = LoggerFactory.getLogger(DrugMain.class);

    private static final String urlPrefix = "http://yao.xywy.com";

    private static final Integer minPage = 1;

    private static final Integer maxPage = 84;

    @Autowired
    private DrugRepository drugRepository;

    @Test
    public void run() {
        for (int i = minPage; i <= maxPage; i++) {
            logger.info("======================================< 第 {} 页 >======================================", i);
            pageDrugs(i);
        }
    }

    /**
     * {
     *     "query": {
     *         "bool": {
     *             "should": [
     *                 {
     *                     "match": {
     *                         "name": {
     *                             "query": "眼睛疼",
     *                             "boost": 2
     *                         }
     *                     }
     *                 },
     *                 {
     *                     "match": {
     *                         "content": {
     *                             "query": "眼睛疼"
     *                         }
     *                     }
     *                 }
     *             ]
     *         }
     *     },
     *     "size": 10
     * }
     */
    @Test
    public void search() {
        MatchQueryBuilder matchQueryBuilder1 = new MatchQueryBuilder("name", "眼睛疼");
        MatchQueryBuilder matchQueryBuilder2 = new MatchQueryBuilder("content", "眼睛疼");
        matchQueryBuilder1.boost(2);
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.should(matchQueryBuilder1).should(matchQueryBuilder2);
        Pageable pageable = PageRequest.of(0, 10);
        Iterable<Drug> drugs = drugRepository.search(boolQueryBuilder, pageable);
        drugs.forEach(drug -> logger.info(drug.toString()));
    }

    private void pageDrugs(int page) {
        try {
            Document doc = Jsoup.connect(urlPrefix + "/class/412-0-0-1-0-" + page + ".htm").get();
            Elements drugs = doc.select(".h-drugs-item");
            for (Element drug : drugs) {
                Drug d = new Drug();

                Element name = drug.child(0).child(0);
                String href = name.attr("href");
                String id = StrUtil.replace(StrUtil.subSuf(
                        href,
                        StrUtil.lastIndexOfIgnoreCase(href, "/") + 1
                ), ".htm", "");

                d.setId(id);
                d.setName(name.text());

                d.setFactory(drug.child(0).child(1).text());
                try {
                    d.setPrice(new BigDecimal(drug.child(0).child(2).child(0).child(0).text()));
                } catch (Exception e) {
                    d.setPrice(new BigDecimal(0));
                }
                d.setContent(drug.child(1).child(1).child(1).text());

                d.setApprovalNo("国药准字:" + id);

                loadDetail(d, href);

                logger.info(d.toString());

                drugRepository.save(d);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void loadDetail(Drug drug, String detailUrl) throws IOException {
        Document doc = Jsoup.connect(urlPrefix + detailUrl).get();
        Elements dis = doc.select(".d-info-dl dl:eq(4) dd a");
        dis.forEach(new Consumer<Element>() {
            @Override
            public void accept(Element element) {
                if (drug.getDisease() == null) {
                    drug.setDisease(new ArrayList<>(dis.size()));
                }
                drug.getDisease().add(element.text());
            }
        });
    }
}
