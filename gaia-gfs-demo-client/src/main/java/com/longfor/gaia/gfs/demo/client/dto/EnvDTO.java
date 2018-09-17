package com.longfor.gaia.gfs.demo.client.dto;

import com.longfor.gaia.gfs.web.mock.strategy.NameCN;
import com.longfor.gaia.gfs.web.mock.strategy.NameEN;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:12
 */
@ApiModel("env 信息")
@Data
@NoArgsConstructor
public class EnvDTO implements Serializable {

    @ApiModelProperty(value = "自增id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "环境名称", example = "dev")
    private String name;
    @ApiModelProperty(value = "环境描述", example = "研发环境")
    private String description;
    @ApiModelProperty(value = "创建者", example = "shanhonghao")
    @PodamStrategyValue(NameCN.class)
    private String createBy;
    @ApiModelProperty(value = "修改者", example = "shanhonghao")
    @PodamStrategyValue(NameEN.class)
    private String updateBy;
    @ApiModelProperty(value = "创建时间", example = "2018-01-01T01:00:00")
    private Date createTime;
    @ApiModelProperty(value = "修改时间", example = "2018-01-01T01:00:00")
    private Date updateTime;
}
