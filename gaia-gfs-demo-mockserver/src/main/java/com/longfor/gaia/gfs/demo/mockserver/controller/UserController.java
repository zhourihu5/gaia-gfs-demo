package com.longfor.gaia.gfs.demo.mockserver.controller;

import com.codahale.metrics.annotation.Timed;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.demo.client.dto.UserDTO;
import com.longfor.gaia.gfs.demo.client.req.CreateUserReq;
import com.longfor.gaia.gfs.demo.client.req.UpdateUserReq;
import com.longfor.gaia.gfs.web.core.ApiVersion;
import com.longfor.gaia.gfs.web.mock.MockOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author shanhonghao
 * @since 2018-09-25 09:24
 */
@Api(value = "用户相关API", tags = {"user"})
@RestController
@RequestMapping("api/users")
@ApiVersion(1)
@Slf4j
public class UserController {

    @Timed
    @ApiOperation(value = "分页获取用户列表", notes = "curl -X GET \"http://127.0.0.1:8080/api/users?pageNum=0&pageSize=10\" -H \"accept: application/json;charset=UTF-8\"")
    @MockOperation
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<PageInfo<UserDTO>> pagianteUsers(
            @ApiParam(value = "当前页码", required = false)
            @Valid @Min(0)
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,

            @ApiParam(value = "分页大小", required = false)
            @Valid @Min(1)
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return null;
    }

    @Timed
    @ApiOperation(value = "获取指定 id 的用户信息", notes = "curl -X GET \"http://127.0.0.1:8080/api/users/1\" -H \"accept: application/json;charset=UTF-8\"")
    @MockOperation
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<UserDTO> loadUserById(
            @ApiParam(value = "用户 id", required = true)
            @Valid @Min(1)
            @PathVariable("userId") Integer id
    ) {
        return null;
    }

    @Timed
    @ApiOperation("创建用户信息")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @MockOperation
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<UserDTO> createUser(
            @Valid
            @RequestBody CreateUserReq userReq
    ) {
        return null;
    }

    @Timed
    @ApiOperation("修改用户")
    @PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @MockOperation
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<UserDTO> updateUserById(
            @ApiParam(value = "用户 id", required = true)
            @Valid @Min(1)
            @PathVariable("userId") Integer id,

            @Valid
            @RequestBody UpdateUserReq userReq
    ) {
        return null;
    }
}
