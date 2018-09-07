package com.longfor.daenerys3.demo.web.controller;

import com.longfor.daenerys3.core.response.BaseResponse;
import com.longfor.daenerys3.web.core.config.GitVersionProperties;
import com.longfor.daenerys3.web.core.dto.GitVersionDTO;
import com.longfor.daenerys3.web.core.mapper.GitVersionMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shanhonghao
 * @since 2018-08-15 15:23
 */
@RestController
@RequestMapping("api/git")
public class GitController {

    @Resource
    private GitVersionProperties gitVersionProperties;
    @Resource
    private GitVersionMapper gitVersionMapper;

    @GetMapping(value = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse<GitVersionDTO> fetchGitInfo() {
        return new BaseResponse<>(gitVersionMapper.toDTO(this.gitVersionProperties));
    }
}
