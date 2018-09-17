package com.longfor.gaia.gfs.demo.web.repo.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.gaia.gfs.demo.client.dto.EnvDTO;
import com.longfor.gaia.gfs.demo.web.convertor.EnvConvertor;
import com.longfor.gaia.gfs.demo.web.repo.EnvRepo;
import com.longfor.gaia.gfs.demo.web.repo.dao.condition.EnvPaginateCondition;
import com.longfor.gaia.gfs.demo.web.repo.dao.entity.Env;
import com.longfor.gaia.gfs.demo.web.repo.dao.mapper.EnvMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.exceptions.JedisConnectionException;

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
    @Resource
    private RedisTemplate demoRedis;

    @Override
    @LFAssignDataSource("custom01")
    public Optional<EnvDTO> loadEnvById(Integer envId) {
        String redisKey = redisKey(envId);
        Env env = loadInCache(redisKey);

        if (env == null) {
            env = envMapper.selectByPrimaryKey(envId);
            if (env != null) {
                cacheEnv(redisKey, env);
            }
        }
        return Optional.ofNullable(EnvConvertor.toDTO(env));
    }

    private Env loadInCache(String redisKey) {
        try {
            return (Env) demoRedis.opsForValue().get(redisKey);
        } catch (JedisConnectionException e) {
            log.warn("Connect redis failed. {}", e.getMessage());
            return null;
        }
    }

    private void cacheEnv(String redisKey, Env env) {
        demoRedis.opsForValue().set(redisKey, env);
    }

    @Override
    public PageInfo<EnvDTO> paginateEnvs(EnvPaginateCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<Env> envs = envMapper.paginate(condition);
        PageInfo pageInfo = new PageInfo(envs);
        pageInfo.setList(envs.stream().map(EnvConvertor::toDTO).collect(Collectors.toList()));
        return pageInfo;
    }

    private String redisKey(Integer envId) {
        return String.format("gfs:env:id:%s", envId);
    }

}
