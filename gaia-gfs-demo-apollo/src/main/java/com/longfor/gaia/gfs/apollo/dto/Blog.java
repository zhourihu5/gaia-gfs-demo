package com.longfor.gaia.gfs.apollo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Data
@ApiModel(description = "Blog")
public class Blog {
    @ApiModelProperty(value = "博客id", readOnly = true, position = 0)
    private String id;

    @ApiModelProperty(value = "博客title", example = "First Blog",position = 1)
    @NotBlank
    private String title;

    @ApiModelProperty(value = "博客内容", example = "The details of the first blog, ...",position = 2)
    @NotBlank
    private String body;

    @ApiModelProperty(value = "创建时间", readOnly = true, position = 3)
    private Date createTime;
}
