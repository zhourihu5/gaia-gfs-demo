package com.longfor.gaia.gfs.demo.unittest.repo.entity;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author shanhonghao
 * @date 2018-11-09 16:29
 */
@Setter
@Getter
@Table(name = "test_user")
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "user_id")
    private String userId;
    private String username;
}
