package com.longfor.gaia.gfs.demo.web.repo.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author shanhonghao
 * @date 2018-05-24 16:20
 */
@Data
@NoArgsConstructor
//@NameStyle(Style.camelhump)
public class Env {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String description;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

}
