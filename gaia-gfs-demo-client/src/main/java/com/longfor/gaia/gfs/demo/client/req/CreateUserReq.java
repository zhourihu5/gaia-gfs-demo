package com.longfor.gaia.gfs.demo.client.req;

import com.longfor.gaia.gfs.demo.client.dto.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shanhonghao
 * @since 2018-09-25 09:36
 */
@ApiModel("创建用户 req")
@Setter
@Getter
public class CreateUserReq {

    @ApiModelProperty(value = "用户名", example = "danshan")
    private String username;
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
