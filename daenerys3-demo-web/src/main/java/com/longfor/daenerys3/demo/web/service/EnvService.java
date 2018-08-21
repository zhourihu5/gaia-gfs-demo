package com.longfor.daenerys3.demo.web.service;

import com.github.pagehelper.PageInfo;
import com.longfor.daenerys3.demo.client.dto.EnvDTO;

import java.util.Optional;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:19
 */
public interface EnvService {

    Optional<EnvDTO> loadEnvById(Integer envId);

    PageInfo<EnvDTO> paginateEnvs(Integer pageNum, Integer pageSize, String name);

}
