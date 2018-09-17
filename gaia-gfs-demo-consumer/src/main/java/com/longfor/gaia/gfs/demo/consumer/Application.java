package com.longfor.gaia.gfs.demo.consumer;

import com.longfor.gaia.gfs.web.feign.EnableLFFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author shanhonghao
 * @since 2018-08-21 14:46
 */
@SpringBootApplication(scanBasePackages = "com.longfor")
@EnableDiscoveryClient
@EnableLFFeignClients(basePackages = "com.longfor")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).getEnvironment();
    }
}
