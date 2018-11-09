package com.longfor.gaia.gfs.demo.unittest.controller;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.demo.unittest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 这是一个最简单的 mockito 的用法, 我们直接把 service 层全部mock, 并模拟各种情况下的处理逻辑
 *
 * @author shanhonghao
 * @date 2018-11-09 22:37
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    /**
     * @InjectMocks 声明这个对象中的属性会被自动注入进来, 也就是下面 mock 出来的 userService
     * 注意这里说的 mock 出来的对象, 包括 @Mock 和 @Spy 声明的对象
     */
    @InjectMocks
    private UserController userController;

    /**
     * 使用 @Mock 生成的类, 所有方法都不是真实的方法, 而且返回值都是NULL.
     * 与之对应的还有个 @Spy
     * 使用 @Spy 生成的类, 所有方法都是真实方法, 返回值都是和真实方法一样的
     */
    @Mock
    private UserService userService;

    @Test
    public void testLoadUserById_success() {
        UserDTO mockUser = mockUser();

        // 这里录制脚本
        // 当我们对 @Mock 的类进行模拟方法时, 会像下面这样去做:
        // when(userService.loadUserById(any(String.class))).thenReturn(Optional.of(mockUser));
        // 当我们对 @Spy 的类进行模拟方法时, 会像下面这样去做:
        // doReturn(Optional.of(mockUser)).when(userService).loadUserById(any(String.class));
        when(userService.loadUserById(any(String.class))).thenReturn(Optional.of(mockUser));

        // 执行你的逻辑
        BaseResponse<UserDTO> response = userController.loadUserById(mockUser.getUserId());

        // 断言
        assertNotNull(response.getData()); // 这句可以不写, 因为为空, 下面的断言会抛 NPE, 直接导致 case 失败
        assertThat(response.getData().getUserId(), is(mockUser.getUserId()));
        assertThat(response.getData().getUsername(), is(mockUser.getUsername()));

        // mock 的验证
        verify(userService, times(1)).loadUserById(any(String.class)); // 有且只有一次执行
    }

    private UserDTO mockUser() {
        UserDTO mock = new UserDTO();
        mock.setUserId("mockId");
        mock.setUsername("mockName");
        return mock;
    }

}