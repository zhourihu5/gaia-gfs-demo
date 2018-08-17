package com.longfor.daenerys3.web.demo.repo.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;

/**
 * @author shanhonghao
 * @since 2018-05-24 16:20
 */
@Data
@NoArgsConstructor
@ToString(exclude = {"createTime", "updateTime"})
public class Env {

    @Id
    private Integer id;
    private String name;
    private String description;
    private boolean deleted;
    private String createBy;
    private String updateBy;

}
