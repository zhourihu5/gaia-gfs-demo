package com.longfor.gaia.gfs.demo.web.repo.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-05-24 16:20
 */
@Data
@NoArgsConstructor
//@NameStyle(Style.camelhump)
public class User {

    @Id
    private Integer id;
    private String username;
    private Integer gender;
    private String name;
    @Column(name = "name_en")
    private String nameEn;
    private String nickname;
    private String province;
    private String city;
    private String cellphone;
    private String email;

    private Boolean deleted;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

}
