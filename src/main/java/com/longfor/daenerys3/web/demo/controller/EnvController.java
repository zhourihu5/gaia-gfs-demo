package com.longfor.daenerys3.web.demo.controller;

import com.longfor.daenerys3.core.response.BaseResponse;
import com.longfor.daenerys3.web.demo.repo.dao.entity.Env;
import com.longfor.daenerys3.web.demo.repo.dao.mapper.EnvMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author shanhonghao
 * @since 2018-08-16 18:16
 */
@RestController
@RequestMapping("api/envs")
public class EnvController {

    @Resource
    private EnvMapper envMapper;

    @GetMapping(value = "",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse pagianteEnvsByNames(
            @RequestParam(value = "name", required = false) String name
    ) {
        if (StringUtils.isBlank(name)) {
            return new BaseResponse(envMapper.selectAll());
        } else {
            return new BaseResponse(envMapper.paginateByName(StringUtils.trimToEmpty(name)));
        }
    }

    @GetMapping(value = "{envId}",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse loadEnvById(@PathVariable("envId") Integer envId) {
        Env env = envMapper.selectByPrimaryKey(envId);
        return new BaseResponse(env);
    }

}
