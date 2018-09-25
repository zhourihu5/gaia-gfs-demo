package com.longfor.gaia.gfs.demo.web.repo.dao.condition;

import com.longfor.gaia.gfs.data.mybatis.PaginateCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shanhonghao
 * @since 2018-08-17 14:06
 */
@Getter
@Setter
public class UserPaginateCondition extends PaginateCondition {
    private Boolean deleted;

    public UserPaginateCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
