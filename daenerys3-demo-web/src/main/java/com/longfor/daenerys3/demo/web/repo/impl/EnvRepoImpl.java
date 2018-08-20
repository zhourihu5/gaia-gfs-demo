package com.longfor.daenerys3.demo.web.repo.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.daenerys3.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.daenerys3.demo.client.EnvDTO;
import com.longfor.daenerys3.demo.web.convertor.EnvConvertor;
import com.longfor.daenerys3.demo.web.repo.EnvRepo;
import com.longfor.daenerys3.demo.web.repo.dao.condition.EnvPaginateCondition;
import com.longfor.daenerys3.demo.web.repo.dao.entity.Env;
import com.longfor.daenerys3.demo.web.repo.dao.mapper.EnvMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:28
 */
@Repository
@Slf4j
public class EnvRepoImpl implements EnvRepo {

    @Resource
    private EnvMapper envMapper;

    @Override
    @LFAssignDataSource("custom01")
    public Optional<EnvDTO> loadEnvById(Integer envId) {
        Env env = envMapper.selectByPrimaryKey(envId);
        return Optional.ofNullable(EnvConvertor.toDTO(env));
    }

    @Override
    public PageInfo<EnvDTO> paginateEnvs(EnvPaginateCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<Env> envs = envMapper.paginate(condition);
        PageInfo pageInfo = new PageInfo(envs);
        pageInfo.setList(envs.stream().map(EnvConvertor::toDTO).collect(Collectors.toList()));
        return pageInfo;
    }

}
