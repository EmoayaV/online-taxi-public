package com.msb.apipaggenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: ApiPassengerApplication
 * Package: com.msb.apipaggenger
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/24 15:12
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiPassengerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class, args);
    }
}
