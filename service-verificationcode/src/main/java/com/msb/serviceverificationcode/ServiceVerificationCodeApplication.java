package com.msb.serviceverificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: ServiceVerificationCodeApplication
 * Package: com.msb.serviceverificationcode
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/25 15:04
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerificationCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationCodeApplication.class, args);
    }

}
