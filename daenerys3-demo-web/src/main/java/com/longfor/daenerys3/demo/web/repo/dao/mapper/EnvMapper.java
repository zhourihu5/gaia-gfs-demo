package com.longfor.daenerys3.demo.web.repo.dao.mapper;

import com.longfor.daenerys3.data.mybatis.LFMySQLMapper;
import com.longfor.daenerys3.demo.web.repo.dao.entity.Env;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shanhonghao
 * @since 2018-08-16 18:15
 */
public interface EnvMapper extends LFMySQLMapper<Env> {

    List<Env> paginateByName(@Param("name") String name);
}
