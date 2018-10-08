package com.longfor.gaia.gfs.demo.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.longfor.gaia.gfs.demo.client.dto.EnvDTO;
import com.longfor.gaia.gfs.demo.web.repo.EnvRepo;
import com.longfor.gaia.gfs.demo.web.repo.dao.condition.EnvPaginateCondition;
import com.longfor.gaia.gfs.demo.web.service.EnvService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-08-17 11:24
 */
@Service
@Slf4j
public class EnvServiceImpl implements EnvService {

    @Resource
    private EnvRepo envRepo;

    @Override
    public Optional<EnvDTO> loadEnvById(Integer envId) {
        Preconditions.checkArgument(envId != null && envId > 0, "env id should be positive number.");
        return envRepo.loadEnvById(envId);
    }

    @Override
    public PageInfo<EnvDTO> paginateEnvs(Integer pageNum, Integer pageSize, String name) {
        EnvPaginateCondition condition = new EnvPaginateCondition(pageNum, pageSize);
        condition.setName(StringUtils.trimToNull(name));
        return envRepo.paginateEnvs(condition);
    }

}
