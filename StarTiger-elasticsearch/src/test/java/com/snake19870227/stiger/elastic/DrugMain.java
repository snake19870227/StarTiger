package com.snake19870227.stiger.elastic;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.elastic.dao.DrugRepository;
import com.snake19870227.stiger.elastic.entity.po.Drug;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
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

    private static final Integer minPage = 10;

    private static final Integer maxPage = 100;

    @Autowired
    private DrugRepository drugRepository;

    @Test
    public void run() {
        for (int i = minPage; i <= maxPage; i++) {
            pageDrugs(i);
        }
    }

    @Test
    public void search() {
        QueryBuilder builder = new MultiMatchQueryBuilder("肾虚", "name", "content");
        Iterable<Drug> drugs = drugRepository.search(builder);
        drugs.forEach(drug -> logger.info(drug.toString()));
    }

    private void pageDrugs(int page) {
        try {
            Document doc = Jsoup.connect(urlPrefix + "/class/4-0-0-1-0-" + page + ".htm").get();
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
                d.setContent(drug.child(1).child(1).child(1).text());

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
