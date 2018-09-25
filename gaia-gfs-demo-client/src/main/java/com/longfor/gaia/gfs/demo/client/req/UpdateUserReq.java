package com.longfor.gaia.gfs.demo.client.req;

import com.longfor.gaia.gfs.demo.client.dto.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class UpdateUserReq implements Serializable {

    @ApiModelProperty(value = "自增id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "性别", example = "1", allowableValues = "0,1,2")
    private Gender gender;
    @ApiModelProperty(value = "中文名", example = "单弘昊")
    private String name;
    @ApiModelProperty(value = "英文名", example = "Dan")
    private String nameEn;
    @ApiModelProperty(value = "昵称", example = "FuckGFW")
    private String nickname;
    @ApiModelProperty(value = "手机", example = "18616300000")
    private String cellphone;
    @ApiModelProperty(value = "邮箱", example = "shanhonghao@longfor.com")
    private String email;
    @ApiModelProperty(value = "省份", example = "上海")
    private String province;
    @ApiModelProperty(value = "城市", example = "上海")
    private String city;

}
