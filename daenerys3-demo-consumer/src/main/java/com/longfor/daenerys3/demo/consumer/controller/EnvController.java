package com.longfor.daenerys3.demo.consumer.controller;

import com.longfor.daenerys3.core.response.BaseResponse;
import com.longfor.daenerys3.demo.client.EnvClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shanhonghao
 * @since 2018-08-21 15:03
 */
@RestController
@RequestMapping("api/envs")
public class EnvController {

    @Resource
    private EnvClient envClient;

    @RequestMapping(value = "{envId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse loadEnvById(
            @PathVariable("envId") Integer envId
    ) {
        return envClient.loadEnvById(1);
    }
}
