package com.longfor.gaia.gfs.demo.web.repo;

import com.github.pagehelper.PageInfo;
import com.longfor.gaia.gfs.demo.client.dto.UserDTO;
import com.longfor.gaia.gfs.demo.client.req.CreateUserReq;
import com.longfor.gaia.gfs.demo.client.req.UpdateUserReq;
import com.longfor.gaia.gfs.demo.web.repo.dao.condition.UserPaginateCondition;

import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-09-25 10:06
 */
public interface UserRepo {

    PageInfo<UserDTO> paginateUsers(UserPaginateCondition condition);

    Optional<UserDTO> loadUserById(Integer id);

    Optional<UserDTO> createUser(CreateUserReq userReq);

    Optional<UserDTO> updateUser(UpdateUserReq userReq);

    Optional<UserDTO> deleteUser(Integer id);
}
