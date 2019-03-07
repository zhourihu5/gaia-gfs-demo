package com.longfor.gaia.gfs.demo.unittest.repo;

import com.longfor.gaia.gfs.demo.unittest.controller.UserDTO;

import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:28
 */
public interface UserRepo {
    Optional<UserDTO> loadUserById(String userId);

    Optional<UserDTO> loadByName(String username);
}
