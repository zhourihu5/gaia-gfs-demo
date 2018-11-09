package com.longfor.gaia.gfs.demo.unittest.service;

import com.longfor.gaia.gfs.demo.unittest.controller.UserDTO;
import com.longfor.gaia.gfs.demo.unittest.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepo userRepo;

    @Override
    public Optional<UserDTO> loadUserById(String userId) {
        return userRepo.loadUserById(userId);
    }

}
