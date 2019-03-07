package com.longfor.gaia.gfs.demo.unittest.repo;

import com.longfor.gaia.gfs.data.redis.RedisKey;
import com.longfor.gaia.gfs.demo.unittest.controller.UserDTO;
import com.longfor.gaia.gfs.demo.unittest.repo.entity.User;
import com.longfor.gaia.gfs.demo.unittest.repo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:28
 */
@Repository
@Slf4j
public class UserRepoImpl implements UserRepo {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate demoRedis;

    @Override
    public Optional<UserDTO> loadUserById(String userId) {
        String redisKey = RedisKey.join("uid", userId);
        User user = loadInCache(redisKey);

        if (user == null) {
            user = userMapper.selectByPrimaryKey(userId);
        }
        return Optional.ofNullable(toDTO(user));
    }

    @Override
    public Optional<UserDTO> loadByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        User user = userMapper.selectOneByExample(example);
        return Optional.ofNullable(toDTO(user));
    }

    private User loadInCache(String redisKey) {
        try {
            return (User) demoRedis.opsForValue().get(redisKey);
        } catch (RedisConnectionFailureException e) {
            log.warn("Connect redis failed. {}", e.getMessage());
            return null;
        }
    }

    private UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
