package com.msb.servicedriveruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: ServiceDriverUserApplication
 * Package: com.msb.servicedriveruser
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 14:15
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.msb.servicedriveruser.mapper")
public class ServiceDriverUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class, args);
    }


}
