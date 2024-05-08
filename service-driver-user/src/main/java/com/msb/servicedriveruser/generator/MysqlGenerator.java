package com.msb.servicedriveruser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Connection;
import java.util.Collection;
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
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/service-driver-user?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","root")
                .globalConfig(builder -> {
                    builder.author("emoaya").fileOverride().outputDir("D:\\IDEA\\Code\\online-taxi-public\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.msb.servicedriveruser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,"D:\\IDEA\\Code\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\msb\\servicedriveruser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("driver_user_work_status");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
