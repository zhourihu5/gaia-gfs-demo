package com.longfor.daenerys3.demo.client;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:12
 */
@Data
@NoArgsConstructor
public class EnvDTO {

    private Integer id;
    private String name;
    private String description;
    private String createBy;
    private String updateBy;

}
