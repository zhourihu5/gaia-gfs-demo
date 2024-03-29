package com.longfor.gaia.gfs.demo.web.controller;

import com.codahale.metrics.annotation.Timed;
import com.github.pagehelper.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.demo.client.dto.EnvDTO;
import com.longfor.gaia.gfs.demo.web.service.EnvService;
import com.longfor.gaia.gfs.web.core.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-08-16 18:16
 */
@Api(value = "env 相关API", tags = {"env"})
@RestController
@RequestMapping("api/envs")
@ApiVersion({1, 3})
@Slf4j
public class EnvController {

    @Resource
    private EnvService envService;

    @Timed
    @ApiOperation(value = "分页获取环境列表", notes = "curl -X GET \"http://127.0.0.1:8080/api/envs?pageNum=0&pageSize=10\" -H \"accept: application/json;charset=UTF-8\"")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<PageInfo<EnvDTO>> pagianteEnvs(
            @ApiParam(value = "当前页码", required = false)
            @Valid @Min(0)
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,

            @ApiParam(value = "分页大小", required = false)
            @Valid @Min(1)
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,

            @ApiParam(value = "环境名称", required = false)
            @RequestParam(value = "name", required = false) String name
    ) {
        return new BaseResponse(envService.paginateEnvs(pageNum, pageSize, name));
    }

    @Timed
    @ApiOperation(value = "查询指定id的环境信息", notes = "curl -X GET \"http://127.0.0.1:8080/api/envs/1\" -H \"accept: application/json;charset=UTF-8\"")
    @GetMapping(value = "{envId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiVersion(2)
    public BaseResponse<EnvDTO> loadEnvById(
            @ApiParam(value = "环境id", required = true)
            @Valid @NotNull @Min(1)
            @PathVariable("envId") Integer envId) {
        Optional<EnvDTO> env = envService.loadEnvById(envId);
        BaseResponse<EnvDTO> response = env.map(BaseResponse::new).orElseGet(BaseResponse::new);
        log.info("response: {}", response);
        return response;
    }

}
