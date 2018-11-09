package com.longfor.gaia.gfs.demo.unittest.service;

import com.longfor.gaia.gfs.demo.unittest.controller.UserDTO;

import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:27
 */
public interface UserService {
    Optional<UserDTO> loadUserById(String userId);
}
