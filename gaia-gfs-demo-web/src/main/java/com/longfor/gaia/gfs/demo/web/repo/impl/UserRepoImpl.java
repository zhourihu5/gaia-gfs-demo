package com.longfor.gaia.gfs.demo.web.repo.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.gaia.gfs.demo.client.dto.UserDTO;
import com.longfor.gaia.gfs.demo.client.req.CreateUserReq;
import com.longfor.gaia.gfs.demo.client.req.UpdateUserReq;
import com.longfor.gaia.gfs.demo.web.convertor.UserConvertor;
import com.longfor.gaia.gfs.demo.web.repo.UserRepo;
import com.longfor.gaia.gfs.demo.web.repo.dao.condition.UserPaginateCondition;
import com.longfor.gaia.gfs.demo.web.repo.dao.entity.User;
import com.longfor.gaia.gfs.demo.web.repo.dao.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author shanhonghao
 * @since 2018-09-25 10:07
 */
@Repository
public class UserRepoImpl implements UserRepo {
    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo<UserDTO> paginateUsers(UserPaginateCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<User> users = userMapper.paginate(condition);
        PageInfo pageInfo = new PageInfo(users);
        pageInfo.setList(users.stream().map(UserConvertor::toDTO).collect(Collectors.toList()));
        return pageInfo;
    }

    @Override
    public Optional<UserDTO> loadUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return (user == null || user.getDeleted()) ? Optional.empty() : Optional.of(UserConvertor.toDTO(user));
    }

    @Override
    public Optional<UserDTO> createUser(CreateUserReq userReq) {
        User po = UserConvertor.toPO(userReq);
        po.setDeleted(false);
        userMapper.create(po);
        return Optional.ofNullable(UserConvertor.toDTO(userMapper.selectByPrimaryKey(po.getId())));
    }

    @Override
    public Optional<UserDTO> updateUser(UpdateUserReq userReq) {
        userMapper.updateById(UserConvertor.toPO(userReq));
        return Optional.ofNullable(UserConvertor.toDTO(userMapper.selectByPrimaryKey(userReq)));
    }
}
