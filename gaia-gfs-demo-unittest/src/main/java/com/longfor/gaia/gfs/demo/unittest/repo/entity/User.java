package com.longfor.gaia.gfs.demo.unittest.repo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:29
 */
@Setter
@Getter
public class User implements Serializable {
    private String userId;
    private String username;
}
