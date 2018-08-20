package com.longfor.daenerys3.demo.web.controller;

import com.github.pagehelper.PageInfo;
import com.longfor.daenerys3.core.response.BaseResponse;
import com.longfor.daenerys3.demo.client.EnvDTO;
import com.longfor.daenerys3.demo.web.service.EnvService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author shanhonghao
 * @since 2018-08-16 18:16
 */
@Api(value = "env 相关API", tags = {"env"})
@RestController
@RequestMapping("api/envs")
public class EnvController {

    @Resource
    private EnvService envService;

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

    @ApiOperation(value = "查询指定id的环境信息", notes = "curl -X GET \"http://127.0.0.1:8080/api/envs/1\" -H \"accept: application/json;charset=UTF-8\"")
    @GetMapping(value = "{envId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<EnvDTO> loadEnvById(
            @ApiParam(value = "环境id", required = true)
            @Valid @NotNull @Min(1)
            @PathVariable("envId") Integer envId) {
        Optional<EnvDTO> env = envService.loadEnvById(envId);
        return env.map(BaseResponse::new).orElseGet(BaseResponse::new);
    }

}
