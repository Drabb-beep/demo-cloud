package com.demo.vite;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MysqlGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MysqlGenerator.class);
    /**
     * 数据源配置（修改成MySQL）
     */
    protected static String URL = "jdbc:mysql://127.0.0.1:3306/vite-demo?userSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
    protected static String USERNAME = "root";
    protected static String PASSWORD = "123456";

    protected static DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD);

    // 处理 all 情况
    /**
     * 交互式的生成器（all）
     * @param tables
     * @return
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
//                全局配置
                .globalConfig((scanner,builder) ->
//                    builder就是globalConfig的构建器
                                builder //覆盖已生成文件

                                        .outputDir(System.getProperty("user.dir")+"\\demo-services\\demo-modules\\demo-vite-service\\src\\main\\java")
                                        .author("df")
//                                        .enableSwagger()
                                        .commentDate("yyyy-MM-dd")
                                        .build() //构建器开始工作

                )
//                包配置
                .packageConfig(builder-> {
                    builder
                            .parent("com.demo.vite")
                            .entity("model")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\demo-services\\demo-modules\\demo-vite-service\\src\\main\\resources\\mapper"))
                            .build();
                })
//                注入配置
                .injectionConfig((builder) ->
                        builder.beforeOutputFile(
                                (a, b) -> logger.warn("tableInfo: " + a.getEntityName())
                        )
                )
//                策略配置
                .strategyConfig((scanner, builder) ->
                        builder.addInclude(getTables("all"))
//                                .addTablePrefix("")
                                .entityBuilder()
                                .enableFileOverride()
                                .enableChainModel()
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                .addTableFills(
                                        new Column("create_time", FieldFill.INSERT),
                                        new Column("update_time", FieldFill.INSERT_UPDATE)
                                )
                                .controllerBuilder()
                                .enableRestStyle()
                                .enableHyphenStyle()
                                .build())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
