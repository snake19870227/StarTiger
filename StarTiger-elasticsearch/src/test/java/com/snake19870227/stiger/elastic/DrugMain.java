package com.snake19870227.stiger.elastic;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.elastic.dao.DrugRepository;
import com.snake19870227.stiger.elastic.entity.po.Drug;
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

/**
 * @author Bu HuaYang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugMain {

    private static final Logger logger = LoggerFactory.getLogger(DrugMain.class);

    private static final Integer maxPage = 300;

    @Autowired
    private DrugRepository drugRepository;

    @Test
    public void run() {
        for (int i = 1; i <= maxPage; i++) {
            pageDrugs(i);
        }
    }

    private void pageDrugs(int page) {
        try {
            Document doc = Jsoup.connect("http://yao.xywy.com/class/4-0-0-1-0-" + page + ".htm").get();
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

                logger.info(d.toString());

                drugRepository.save(d);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
