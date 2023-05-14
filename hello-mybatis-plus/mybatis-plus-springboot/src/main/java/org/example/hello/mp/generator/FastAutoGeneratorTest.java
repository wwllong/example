package org.example.hello.mp.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author jack.wen
 * @since 2023/5/15 01:37
 */
public class FastAutoGeneratorTest {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://127.0.0.1:3306/mybatis_plus?characterEncoding=utf-8", "root", "123456");

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + "/mybatis-plus-springboot";
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("jack.wen") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java") // 指定输出目录
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("org.example.hello.mp.generator") // 设置父包名
                            .moduleName("test") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
