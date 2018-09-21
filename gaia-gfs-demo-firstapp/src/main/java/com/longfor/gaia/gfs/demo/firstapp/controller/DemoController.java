package com.longfor.gaia.gfs.demo.firstapp.controller;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shanhonghao
 * @since 2018-09-19 08:34
 */
@RestController
@Slf4j
public class DemoController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<String> demo() {
        BaseResponse<String> response = new BaseResponse<>("demo");
        new T().start();

        log.info("{}", response);
        return response;
    }

    public class T extends Thread {
        @Override
        public void run() {
            log.info("test");
            new Demo().ping();
        }
    }
}
