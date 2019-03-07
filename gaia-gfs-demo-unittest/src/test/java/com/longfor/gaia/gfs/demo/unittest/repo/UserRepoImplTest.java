package com.longfor.gaia.gfs.demo.unittest.repo;

import com.longfor.gaia.gfs.data.redis.RedisKey;
import com.longfor.gaia.gfs.demo.unittest.controller.UserDTO;
import com.longfor.gaia.gfs.demo.unittest.repo.entity.User;
import com.longfor.gaia.gfs.demo.unittest.repo.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import tk.mybatis.mapper.entity.Example;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * 这里例子有几个特殊的地方, 被测逻辑中调用了一个 static 方法, 即 RedisKey.join()
 * 由于 mockito 的天生缺陷, 无法模拟构造方法, 静态方法, 私有方法, 所以我们在这里使用 PowerMockito.
 * https://www.ibm.com/developerworks/cn/java/j-lo-powermock/index.html 这里有几个比较简单的例子.
 * @author shanhonghao
 * @date 2018-11-09 17:20
 */
@RunWith(PowerMockRunner.class) // 这里使用 PowerMockRunner
public class UserRepoImplTest {

    @InjectMocks
    private UserRepoImpl userRepo;

    @Mock
    private RedisTemplate demoRedis;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ValueOperations valueOperations;
    @Mock
    private Example example;
    @Mock
    private Example.Criteria criteria;

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

        Optional<UserDTO> userOptional = userRepo.loadUserById(mockUser.getUserId());
        assertTrue(userOptional.isPresent());
        assertThat(userOptional.get().getUserId(), is(mockUser.getUserId()));
        assertThat(userOptional.get().getUsername(), is(mockUser.getUsername()));

        verify(valueOperations, Mockito.times(1)).get(any(String.class));
        verify(userMapper, Mockito.times(1)).selectByPrimaryKey(any(String.class));
    }

    @Test
    @PrepareForTest({Example.class, UserRepoImpl.class, RedisKey.class}) // 声明这个 test 会使用 powermock 针对 RedisKey 有特殊操作.
    public void loadUserByUsername() throws Exception {
        User mockUser = mockUser();

        whenNew(Example.class).withAnyArguments().thenReturn(example);
        when(example.createCriteria()).thenReturn(criteria);

        when(userMapper.selectOneByExample(any(Example.class))).thenReturn(mockUser);

        Optional<UserDTO> user = userRepo.loadByName(UUID.randomUUID().toString());
        assertThat(user.get().getUsername(), is(mockUser.getUsername()));

    }

    private User mockUser() {
        User mock = new User();
        mock.setUserId("mockId");
        mock.setUsername("mockName");
        return mock;
    }
}