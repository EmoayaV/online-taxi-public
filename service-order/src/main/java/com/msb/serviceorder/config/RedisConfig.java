package com.msb.serviceorder.config;

import com.alibaba.nacos.api.common.Constants;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ClassName: RedisConfig
 * Package: com.msb.serviceorder.config
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/7/1 19:16
 * @Version 1.0
 */


@Component
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.111.128:6379").setDatabase(0);
        return Redisson.create(config);
    }


}
