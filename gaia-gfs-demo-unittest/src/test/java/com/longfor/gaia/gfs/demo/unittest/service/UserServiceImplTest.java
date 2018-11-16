package com.longfor.gaia.gfs.demo.unittest.service;

import com.longfor.gaia.gfs.data.redis.RedisKey;
import com.longfor.gaia.gfs.demo.unittest.controller.UserDTO;
import com.longfor.gaia.gfs.demo.unittest.repo.UserRepoImpl;
import com.longfor.gaia.gfs.demo.unittest.repo.entity.User;
import com.longfor.gaia.gfs.demo.unittest.repo.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * 这里演示的是 @Spy, @Mock 和 @InjectMocks 的结合使用
 * @author shanhonghao
 * @date 2018-11-10 12:56
 */
@RunWith(PowerMockRunner.class) // 这里使用 PowerMockRunner
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Spy // 这里表示, 下面的 userRepo 是个被 mockito 代理的 UserRepoImpl, 会被注入到上面的 userService 中,
    @InjectMocks // 这句表示, userRepo 中的依赖可以被注入
    private UserRepoImpl userRepo = new UserRepoImpl();

    @Mock // 这里就是把 userMapper 注入到上面的 userRepo中, 由于 userMapper 是 @Mock, 所以所有方法全返回 null
    private UserMapper userMapper;
    @Mock
    private RedisTemplate demoRedis;
    @Mock
    private ValueOperations valueOperations;

    @Before
    public void setUp() {
        mockStatic(RedisKey.class); // 开始 mock static 方法, 注意下面的 when 是 powermock 的 when
        when(RedisKey.join(any(String.class))).thenReturn("rediskey");
        when(demoRedis.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    @PrepareForTest(RedisKey.class) // 声明这个 test 会使用 powermock 针对 RedisKey 有特殊操作.
    public void loadUserById_fromDb() {
        User mockUser = mockUser();

        when(valueOperations.get(any(String.class))).thenReturn(null);
        when(userMapper.selectByPrimaryKey(any(String.class))).thenReturn(mockUser);

        Optional<UserDTO> userOptional = userService.loadUserById(mockUser.getUserId());
        assertTrue(userOptional.isPresent());
        assertThat(userOptional.get().getUserId(), is(mockUser.getUserId()));
        assertThat(userOptional.get().getUsername(), is(mockUser.getUsername()));

        verify(valueOperations, Mockito.times(1)).get(any(String.class));
        verify(userMapper, Mockito.times(1)).selectByPrimaryKey(any(String.class));
    }

    private User mockUser() {
        User mock = new User();
        mock.setUserId("mockId");
        mock.setUsername("mockName");
        return mock;
    }

}