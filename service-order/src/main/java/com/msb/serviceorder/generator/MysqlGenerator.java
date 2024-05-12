package com.msb.serviceorder.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * ClassName: MysqlGenerator
 * Package: com.msb.servicedriveruser.generator
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 21:32
 * @Version 1.0
 */
public class MysqlGenerator {
    public static void main(String[] args) {

        //自动生成代码工具类
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/service-order?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","root")
                .globalConfig(builder -> {
                    builder.author("emoaya").fileOverride().outputDir("D:\\IDEA\\Code\\online-taxi-public\\service-order\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.msb.serviceorder").pathInfo(Collections.singletonMap(OutputFile.mapperXml,"D:\\IDEA\\Code\\online-taxi-public\\service-order\\src\\main\\java\\com\\msb\\serviceorder\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order_info");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
