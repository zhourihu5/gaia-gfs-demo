package com.longfor.gaia.gfs.demo.consumer.controller;

import com.longfor.gaia.gfs.core.bean.BaseResponse;
import com.longfor.gaia.gfs.demo.client.EnvClient;
import com.longfor.gaia.gfs.demo.client.dto.EnvDTO;
import com.longfor.gaia.gfs.web.mock.MockOperation;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation(value = "分页获取环境列表")
    @MockOperation
    @GetMapping(value = "{envId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<EnvDTO> loadEnvById(@PathVariable("envId") Integer envId) {
        return envClient.loadEnvById(envId);
    }
}
