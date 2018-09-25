package com.longfor.gaia.gfs.demo.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.longfor.gaia.gfs.demo.client.dto.UserDTO;
import com.longfor.gaia.gfs.demo.client.req.CreateUserReq;
import com.longfor.gaia.gfs.demo.client.req.UpdateUserReq;
import com.longfor.gaia.gfs.demo.web.repo.UserRepo;
import com.longfor.gaia.gfs.demo.web.repo.dao.condition.UserPaginateCondition;
import com.longfor.gaia.gfs.demo.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:24
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepo userRepo;

    @Override
    public PageInfo<UserDTO> paignateUsers(Integer pageNum, Integer pageSize) {
        UserPaginateCondition condition = new UserPaginateCondition(pageNum, pageSize);
        condition.setDeleted(false);
        return userRepo.paginateUsers(condition);
    }

    @Override
    public Optional<UserDTO> loadUserById(Integer id) {
        Preconditions.checkArgument(id != null && id > 0, "user id should be positive number.");
        return userRepo.loadUserById(id);
    }

    @Override
    public Optional<UserDTO> createUser(CreateUserReq userReq) {
        Preconditions.checkArgument(userReq != null, "user should not be null.");
        return userRepo.createUser(userReq);
    }

    @Override
    public Optional<UserDTO> updateUserById(UpdateUserReq userReq) {
        Preconditions.checkArgument(userReq != null, "user should not be null.");
        return userRepo.updateUser(userReq);
    }
}
