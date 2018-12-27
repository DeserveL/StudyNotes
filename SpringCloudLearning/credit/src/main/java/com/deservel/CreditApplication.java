package com.deservel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 积分系统
 *
 * @author DeserveL
 * @date 2018-12-27 0:04
 * @since 1.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CreditApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditApplication.class, args);
    }

}

