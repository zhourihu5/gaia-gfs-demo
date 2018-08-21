package com.longfor.daenerys3.demo.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:12
 */
@ApiModel("env 信息")
@Data
@NoArgsConstructor
public class EnvDTO {

    @ApiModelProperty("自增id")
    private Integer id;
    @ApiModelProperty("环境名称")
    private String name;
    @ApiModelProperty("环境描述")
    private String description;
    @ApiModelProperty("创建者")
    private String createBy;
    @ApiModelProperty("修改者")
    private String updateBy;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
