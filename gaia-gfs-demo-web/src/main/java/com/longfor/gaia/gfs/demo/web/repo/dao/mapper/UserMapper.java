package com.longfor.gaia.gfs.demo.web.repo.dao.mapper;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.gaia.gfs.demo.web.repo.dao.condition.UserPaginateCondition;
import com.longfor.gaia.gfs.demo.web.repo.dao.entity.User;

import java.util.List;

/**
 * @author shanhonghao
 * @since 2018-09-25 10:14
 */
public interface UserMapper extends LFMySQLMapper<User> {

    List<User> paginate(UserPaginateCondition condition);

    int create(User user);

    int updateById(User user);
}
