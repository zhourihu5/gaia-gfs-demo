package com.longfor.gaia.gfs.demo.web.convertor;

import com.longfor.gaia.gfs.demo.client.dto.Gender;
import com.longfor.gaia.gfs.demo.client.dto.UserDTO;
import com.longfor.gaia.gfs.demo.client.req.CreateUserReq;
import com.longfor.gaia.gfs.demo.client.req.UpdateUserReq;
import com.longfor.gaia.gfs.demo.web.repo.dao.entity.User;
import org.springframework.beans.BeanUtils;

/**
 * @author shanhonghao
 * @date 2018-08-17 11:14
 */
@SuppressWarnings("Duplicates")
public class UserConvertor {

    private UserConvertor() {
    }

    public static UserDTO toDTO(User po) {
        if (po == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(po, dto);
        dto.setGender(Gender.of(po.getGender()));
        return dto;
    }

    public static User toPO(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User po = new User();
        BeanUtils.copyProperties(dto, po);
        po.setGender(dto.getGender().value);
        return po;
    }

    public static User toPO(CreateUserReq dto) {
        if (dto == null) {
            return null;
        }
        User po = new User();
        BeanUtils.copyProperties(dto, po);
        po.setGender(dto.getGender().value);
        return po;
    }

    public static User toPO(UpdateUserReq dto) {
        if (dto == null) {
            return null;
        }
        User po = new User();
        BeanUtils.copyProperties(dto, po);
        po.setGender(dto.getGender().value);
        return po;
    }

}
