package com.snake19870227.springboot.excel.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.snake19870227.springboot.excel.view.DefaultXlsView;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/04/07
 */
@Controller
@RequestMapping(path = "/excel")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private final DefaultXlsView xlsView;

    public IndexController(DefaultXlsView xlsView) {
        this.xlsView = xlsView;
    }

    @GetMapping(path = "/export")
    public ModelAndView export() {
        List<String> carNames = Arrays.asList(
                "车1","车2","车3","车4","车5","车6","车7","车8","车9"
        );
        Map<String, Object> model = new HashMap<>(1);
        model.put("data", carNames);

        xlsView.dataInjector((m, workbook) -> {
            Object data = m.get("data");
            List<String> datas = (List<String>) data;

            Sheet carsSheet = workbook.createSheet("cars");
            Row headRow = carsSheet.createRow(0);
            Cell headNameCell = headRow.createCell(0);
            headNameCell.setCellValue("名称");

            AtomicInteger rowNum = new AtomicInteger(1);
            datas.forEach(v -> {
                Row dataRow = carsSheet.createRow(rowNum.getAndIncrement());
                dataRow.createCell(0).setCellValue(v);
            });
        });

        return new ModelAndView(xlsView, model);
    }
}
