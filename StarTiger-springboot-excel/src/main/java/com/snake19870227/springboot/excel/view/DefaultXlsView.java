package com.snake19870227.springboot.excel.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.function.BiConsumer;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/04/07
 */
@Component
public class DefaultXlsView extends AbstractXlsView {

    private BiConsumer<Map<String, Object>, Workbook> dataInjector;

    public void dataInjector(BiConsumer<Map<String, Object>, Workbook> injector) {
        this.dataInjector = injector;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        Assert.notNull(model, "model can not be null");
        dataInjector.accept(model, workbook);

    }
}
