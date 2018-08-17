package com.longfor.daenerys3.demo.web.repo.dao.condition;

import com.longfor.daenerys3.data.mybatis.PaginateCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shanhonghao
 * @since 2018-08-17 14:06
 */
@Getter
@Setter
public class EnvPaginateCondition extends PaginateCondition {
    private String name;

    public EnvPaginateCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
