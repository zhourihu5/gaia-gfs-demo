package com.longfor.gaia.gfs.demo.client.dto;

import com.longfor.gaia.gfs.web.mock.strategy.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:12
 */
@ApiModel("用户信息")
@Setter
@Getter
@NoArgsConstructor
public class UserDTO implements Serializable {

    @ApiModelProperty(value = "自增id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户名", example = "danshan")
    private String username;
    @ApiModelProperty(value = "性别", example = "1", allowableValues = "0,1,2")
    private Gender gender;
    @PodamStrategyValue(NameCN.class)
    @ApiModelProperty(value = "中文名", example = "单弘昊")
    private String name;
    @PodamStrategyValue(NameEN.class)
    @ApiModelProperty(value = "英文名", example = "Dan")
    private String nameEn;
    @ApiModelProperty(value = "昵称", example = "FuckGFW")
    private String nickname;
    @PodamStrategyValue(CellPhone.class)
    @ApiModelProperty(value = "手机", example = "18616300000")
    private String cellphone;
    @PodamStrategyValue(Email.class)
    @ApiModelProperty(value = "邮箱", example = "shanhonghao@longfor.com")
    private String email;
    @PodamStrategyValue(Province.class)
    @ApiModelProperty(value = "省份", example = "上海")
    private String province;
    @PodamStrategyValue(Province.class)
    @ApiModelProperty(value = "城市", example = "上海")
    private String city;
    @ApiModelProperty(value = "创建时间", example = "2018-01-01T01:00:00")
    private Date createTime;
    @ApiModelProperty(value = "修改时间", example = "2018-01-01T01:00:00")
    private Date updateTime;

}
