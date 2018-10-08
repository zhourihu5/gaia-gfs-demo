package com.longfor.gaia.gfs.demo.web.repo;

import com.github.pagehelper.PageInfo;
import com.longfor.gaia.gfs.demo.client.dto.EnvDTO;
import com.longfor.gaia.gfs.demo.web.repo.dao.condition.EnvPaginateCondition;

import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-08-17 11:26
 */
public interface EnvRepo {

    Optional<EnvDTO> loadEnvById(Integer envId);

    PageInfo<EnvDTO> paginateEnvs(EnvPaginateCondition condition);

}
