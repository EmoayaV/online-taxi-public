package com.msb.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: ServiceMapApplication
 * Package: com.msb.servicemap.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/29 17:08
 * @Version 1.0
 */
@SpringBootApplication
public class ServiceMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class, args);
    }
}