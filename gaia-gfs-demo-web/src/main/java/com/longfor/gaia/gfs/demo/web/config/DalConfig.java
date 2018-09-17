package com.longfor.gaia.gfs.demo.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shanhonghao
 * @since 2018-08-16 18:13
 */
@Configuration
@tk.mybatis.spring.annotation.MapperScan("com.longfor.gaia.gfs.demo.web.repo.dao.mapper")
@EnableTransactionManagement
public class DalConfig {

}
