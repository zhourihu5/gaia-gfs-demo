package com.longfor.gaia.gfs.demo.client.req;

import com.longfor.gaia.gfs.demo.client.dto.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author shanhonghao
 * @since 2018-09-25 09:36
 */
@ApiModel("创建用户 req")
@Setter
@Getter
public class CreateUserReq {

    @NotEmpty
    @Size(max = 100, min = 5)
    @ApiModelProperty(value = "用户名", example = "danshan")
    private String username;

    @NotNull
    @ApiModelProperty(value = "性别", example = "1", allowableValues = "0,1,2")
    private Gender gender;

    @NotEmpty
    @Size(max = 100, min = 2)
    @ApiModelProperty(value = "中文名", example = "单弘昊")
    private String name;

    @NotEmpty
    @Size(max = 100, min = 2)
    @ApiModelProperty(value = "英文名", example = "Dan")
    private String nameEn;

    @NotEmpty
    @Size(max = 100, min = 2)
    @ApiModelProperty(value = "昵称", example = "FuckGFW")
    private String nickname;

    @NotEmpty
    @Digits(fraction = 0, integer = 11)
    @Size(min = 11)
    @ApiModelProperty(value = "手机", example = "18616300000")
    private String cellphone;

    @NotNull
    @Email
    @ApiModelProperty(value = "邮箱", example = "shanhonghao@longfor.com")
    private String email;

    @NotEmpty
    @Size(max = 10, min = 2)
    @ApiModelProperty(value = "省份", example = "上海")
    private String province;

    @NotEmpty
    @Size(max = 32, min = 2)
    @ApiModelProperty(value = "城市", example = "上海")
    private String city;

}
