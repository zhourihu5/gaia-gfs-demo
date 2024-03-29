# gaia-gfs-demo-unittest

---

该项目用于展示如何快速入手单元测试

src/main 中有个最简单的逻辑: 通过 userId 获取 user 信息. 只有两步:

1. 从 redis 中查找用户信息
2. 从数据库中查找用户信息

项目应该是跑不起来的, 因为从配置文件可以发现, 我们尝试连接的 db 和 consul 都是 127.0.0.1, 但对于 Unit Test 来说并不重要.
因为 UT 的编写, 有一个非常重要的原则:

* 拔掉网线也能跑!
* 拔掉网线也!能!跑!!
* 拔!掉!网!线!也!能!跑!!!

重要事情说三遍.

一个提供了 4 个 unit test demo, 涵盖了:

1. mockito 的使用
2. mocktio 模拟异常, 多层 mock 等高级用法
3. 通过内存 h2 数据库 测试 sql

建议的阅读顺序:

1. UserControllerTest.java // 了解 mocktio 的标准用法
2. UserRepoImplTest.java // 用 PowerMock 对 mockito 的一些局限性进行补足(如不支持 static method 的 mock)
3. UserServiceImplTest.java // 多层服务的调用, 用于测试一个完整的业务逻辑

以上三个 demo 连spring 都没用到, 也就是说 完全不依赖你的项目的任何框架, 非常纯粹的 mockito

4. UserMapperTest.java // 集成测试, 测试数据库的 sql