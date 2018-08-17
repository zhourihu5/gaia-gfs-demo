package com.longfor.daenerys3.demo.web.controller;

import com.longfor.daenerys3.core.response.BaseResponse;
import com.longfor.daenerys3.demo.client.EnvDTO;
import com.longfor.daenerys3.demo.web.service.EnvService;
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
@RestController
@RequestMapping("api/envs")
public class EnvController {

    @Resource
    private EnvService envService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse pagianteEnvs(
            @Valid @NotNull @Min(0)
            @RequestParam(value = "pageNum") Integer pageNum,

            @Valid @NotNull @Min(1)
            @RequestParam(value = "pageNum") Integer pageSize,

            @RequestParam(value = "name", required = false) String name
    ) {
        return new BaseResponse(envService.paginateEnvs(pageNum, pageSize, name));
    }

    @GetMapping(value = "{envId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<EnvDTO> loadEnvById(@PathVariable("envId") Integer envId) {
        Optional<EnvDTO> env = envService.loadEnvById(envId);
        return env.map(BaseResponse::new).orElseGet(BaseResponse::new);
    }

}
