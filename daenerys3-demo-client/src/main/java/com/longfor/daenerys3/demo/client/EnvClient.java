package com.longfor.daenerys3.demo.client;

import com.longfor.daenerys3.core.bean.PageInfo;
import com.longfor.daenerys3.core.response.BaseResponse;
import com.longfor.daenerys3.demo.client.dto.EnvDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author shanhonghao
 * @since 2018-08-16 18:16
 */
@Api(value = "env 相关API", tags = {"env"})
@FeignClient("daenerys3-demo-web")
public interface EnvClient {

    String PREFIX = "api/envs";

    @ApiOperation(value = "分页获取环境列表", notes = "curl -X GET \"http://127.0.0.1:8080/api/envs?pageNum=0&pageSize=10\" -H \"accept: application/json;charset=UTF-8\"")
    @RequestMapping(value = PREFIX + "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    BaseResponse<PageInfo<EnvDTO>> pagianteEnvs(
            @ApiParam(value = "当前页码", required = false)
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,

            @ApiParam(value = "分页大小", required = false)
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,

            @ApiParam(value = "环境名称", required = false)
            @RequestParam(value = "name", required = false) String name
    );

    @ApiOperation(value = "查询指定id的环境信息", notes = "curl -X GET \"http://127.0.0.1:8080/api/envs/1\" -H \"accept: application/json;charset=UTF-8\"")
    @RequestMapping(value = PREFIX + "/{envId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    BaseResponse<EnvDTO> loadEnvById(
            @ApiParam(value = "环境id", required = true)
            @PathVariable("envId") Integer envId
    );

}
