package com.longfor.gaia.gfs.demo.web.service;

import com.github.pagehelper.PageInfo;
import com.longfor.gaia.gfs.demo.client.dto.UserDTO;
import com.longfor.gaia.gfs.demo.client.req.CreateUserReq;
import com.longfor.gaia.gfs.demo.client.req.UpdateUserReq;

import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-09-25 09:27
 */
public interface UserService {
    PageInfo<UserDTO> paignateUsers(Integer pageNum, Integer pageSize);

    Optional<UserDTO> loadUserById(Integer id);

    Optional<UserDTO> createUser(CreateUserReq userReq);

    Optional<UserDTO> updateUserById(UpdateUserReq userReq);

    Optional<UserDTO> deleteUserById(Integer id);
}
