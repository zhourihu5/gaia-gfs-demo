package com.longfor.daenerys3.demo.web.repo.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * @author shanhonghao
 * @since 2018-05-24 16:20
 */
@Data
@NoArgsConstructor
public class Env {

    @Id
    private Integer id;
    private String name;
    private String description;
    private String createBy;
    private String updateBy;

}
