package com.longfor.gaia.gfs.demo.unittest.repo.mapper;

import com.longfor.gaia.gfs.demo.unittest.ApplicationTests;
import com.longfor.gaia.gfs.demo.unittest.repo.entity.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * 这个测试是把应用完全起来, 当然, 我们可以在测试的配置文件中去掉很多不需要的配置
 * 通过内存中启动一个 h2 数据库来模拟sql的执行
 * @author shanhonghao
 * @date 2018-11-16 13:43
 */
public class UserMapperTest extends ApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(UUID.randomUUID().toString());
        int result = userMapper.insert(user);

        assertThat(result, is(1));
    }

    @Test
    public void testSelect() {
        User user = userMapper.selectByPrimaryKey("user_id");

        assertNotNull(user);
        assertThat(user.getUsername(), is("username"));
    }

}