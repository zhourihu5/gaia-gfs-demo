package com.longfor.gaia.gfs.demo.unittest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 启动一个测试的进城, 并使用随机的端口
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@RunWith(SpringRunner.class)
public class ApplicationTests {
}