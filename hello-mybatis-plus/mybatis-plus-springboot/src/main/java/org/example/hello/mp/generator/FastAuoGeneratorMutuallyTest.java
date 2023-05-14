package org.example.hello.mp.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jack.wen
 * @since 2023/5/15 01:57
 */
public class FastAuoGeneratorMutuallyTest {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://127.0.0.1:3306/mybatis_plus?characterEncoding=utf-8", "root", "123456");


    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        .fileOverride()
                        .disableOpenDir())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        .enableLombok()
                        .build()
                )
                // 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
