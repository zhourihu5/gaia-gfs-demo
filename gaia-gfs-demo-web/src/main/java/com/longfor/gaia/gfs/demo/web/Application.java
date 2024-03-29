package com.longfor.gaia.gfs.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author shanhonghao
 * @date 2018-08-15 15:20
 */
@SpringBootApplication
@Slf4j
public class Application extends SpringApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(Application.class, args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "服务端 '{}' 启动完成! \n\t" +
                        "环境(s): {}\n\t" +
                        "日志级别: {}" +
                        "\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getActiveProfiles(),
                env.getProperty("logging.level.ROOT"));
    }
}
