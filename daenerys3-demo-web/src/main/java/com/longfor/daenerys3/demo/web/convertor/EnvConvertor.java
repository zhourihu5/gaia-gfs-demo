package com.longfor.daenerys3.demo.web.convertor;

import com.longfor.daenerys3.demo.client.EnvDTO;
import com.longfor.daenerys3.demo.web.repo.dao.entity.Env;
import org.springframework.beans.BeanUtils;

/**
 * @author shanhonghao
 * @since 2018-08-17 11:14
 */
public class EnvConvertor {

    public static EnvDTO toDTO(Env po) {
        if (po == null) {
            return null;
        }
        EnvDTO dto = new EnvDTO();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }
}
