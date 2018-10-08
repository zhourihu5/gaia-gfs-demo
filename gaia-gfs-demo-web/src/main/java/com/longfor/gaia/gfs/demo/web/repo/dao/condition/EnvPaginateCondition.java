package com.longfor.gaia.gfs.demo.web.repo.dao.condition;

import com.longfor.gaia.gfs.data.mybatis.PaginateCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shanhonghao
 * @date 2018-08-17 14:06
 */
@Getter
@Setter
public class EnvPaginateCondition extends PaginateCondition {
    private String name;

    public EnvPaginateCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
