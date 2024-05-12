package com.msb.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: ServiceOrderApplication
 * Package: com.msb.serviceorder
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/10 16:49
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.msb.serviceorder.mapper")
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
