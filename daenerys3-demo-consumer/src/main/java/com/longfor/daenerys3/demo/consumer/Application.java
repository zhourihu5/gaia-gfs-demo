package com.longfor.daenerys3.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author shanhonghao
 * @since 2018-08-21 14:46
 */
@SpringBootApplication(scanBasePackages = "com.longfor")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.longfor")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).getEnvironment();
    }
}
