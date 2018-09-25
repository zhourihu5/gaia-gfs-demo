package com.longfor.gaia.gfs.demo.web.convertor;

import com.longfor.gaia.gfs.demo.client.dto.Gender;
import com.longfor.gaia.gfs.demo.client.dto.UserDTO;
import com.longfor.gaia.gfs.demo.web.repo.dao.entity.User;
import org.springframework.beans.BeanUtils;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:14
 */
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
}
