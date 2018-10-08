package com.longfor.gaia.gfs.demo.client;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.demo.client.dto.EnvDTO;
import com.longfor.gaia.gfs.web.core.ApiVersion;
import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author shanhonghao
 * @date 2018-08-16 18:16
 */
@Api(value = "env 相关API", tags = {"env"})
@LFFeignClient(value = "gaia-gfs-demo-web", group = "gaia-gfs-demo", configuration = LFFeignConfiguration.class)
public interface EnvClient {

    @ApiOperation(value = "分页获取环境列表", notes = "curl -X GET \"http://127.0.0.1:8080/v1/api/envs?pageNum=0&pageSize=10\" -H \"accept: application/json;charset=UTF-8\"")
    @ApiVersion(1)
    @GetMapping(value = "api/envs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    BaseResponse<PageInfo<EnvDTO>> pagianteEnvs(
            @ApiParam(value = "当前页码", required = false)
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,

            @ApiParam(value = "分页大小", required = false)
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,

            @ApiParam(value = "环境名称", required = false)
            @RequestParam(value = "name", required = false) String name
    );

    @ApiOperation(value = "查询指定id的环境信息", notes = "curl -X GET \"http://127.0.0.1:8080/v2/api/envs/1\" -H \"accept: application/json;charset=UTF-8\"")
    @ApiVersion(2)
    @GetMapping(value = "api/envs/{envId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    BaseResponse<EnvDTO> loadEnvById(
            @ApiParam(value = "环境id", required = true)
            @PathVariable("envId") Integer envId
    );

}
