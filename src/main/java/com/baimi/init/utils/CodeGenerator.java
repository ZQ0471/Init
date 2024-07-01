package com.baimi.init.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    private static final String outPutDir = "E:\\code\\tempFiles";
    private static final String tables = "admin_account,admin_role,admin_permission,admin_role_permission,material,category";
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql:///material", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("zhangqi").outputDir(outPutDir); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.pathInfo(Collections.singletonMap(OutputFile.xml, outPutDir+"\\Xml\\mapper")) // 设置mapperXml生成路径
                                .parent("temp") // 设置父包名
                )
                .strategyConfig(builder ->
                        builder.addInclude(tables) // 设置需要生成的表名
                                .addTablePrefix() // 设置过滤表前缀
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
