package com.snake19870227.stiger.mall.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mysql.cj.jdbc.Driver;

/**
 * @author Bu HuaYang
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {

        String[] schemas = new String[] {"StigerMallAccount","StigerMallGoods"};

        for (String schema : schemas) {

            AutoGenerator mpg = new AutoGenerator();

            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/" + schema + "?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
            dsc.setDriverName(Driver.class.getName());
            dsc.setUsername("stigermall");
            dsc.setPassword("123456");
            mpg.setDataSource(dsc);

            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + "/StarTiger-cloud-mall/stiger-cloud-mall-common/src/main/java");
            gc.setAuthor("Bu HuaYang");
            gc.setOpen(false);
            gc.setFileOverride(true);
            gc.setIdType(IdType.ASSIGN_UUID);
            mpg.setGlobalConfig(gc);

            PackageConfig pc = new PackageConfig();
            pc.setParent("com.snake19870227.stiger.mall");
            pc.setEntity("entity.po");
            pc.setMapper("mapper").setXml("mapper");
            mpg.setPackageInfo(pc);

            TemplateConfig tc = new TemplateConfig();
            tc.setService(null).setServiceImpl(null).setController(null);
            mpg.setTemplate(tc);

            StrategyConfig strategy = new StrategyConfig();
            strategy.setEntityBuilderModel(true);
            strategy.setNaming(NamingStrategy.underline_to_camel);
            mpg.setStrategy(strategy);

            mpg.execute();

        }

    }
}
