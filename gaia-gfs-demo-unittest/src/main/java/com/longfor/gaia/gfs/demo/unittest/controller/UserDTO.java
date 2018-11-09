package com.longfor.gaia.gfs.demo.unittest.controller;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:26
 */
@Setter
@Getter
public class UserDTO implements Serializable {

    private String userId;
    private String username;
}
