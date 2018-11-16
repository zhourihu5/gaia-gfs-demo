package com.longfor.gaia.gfs.demo.unittest.controller;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.demo.unittest.service.UserService;
import com.longfor.gaia.gfs.web.core.ApiVersion;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:24
 */
@RestController
@RequestMapping(value = "api/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiVersion(1)
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("{userId}")
    public BaseResponse<UserDTO> loadUserById(@PathVariable String userId) {
        return userService.loadUserById(userId).map(BaseResponse::new).orElseThrow(ResourceNotFoundException::new);
    }

}
