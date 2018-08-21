package com.longfor.daenerys3.demo.web.repo;

import com.github.pagehelper.PageInfo;
import com.longfor.daenerys3.demo.client.dto.EnvDTO;
import com.longfor.daenerys3.demo.web.repo.dao.condition.EnvPaginateCondition;

import java.util.Optional;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:26
 */
public interface EnvRepo {

    Optional<EnvDTO> loadEnvById(Integer envId);

    PageInfo<EnvDTO> paginateEnvs(EnvPaginateCondition condition);

}
