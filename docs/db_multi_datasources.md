# 数据库多数据源

---

spring-boot 默认支持单 datasource, 显然无法满足我们的需要
在原有基础上支持多数据源操作. 纯粹多库, 各个库甚至可以是不同的数据库.

## 约定

* 本框架只做 `切换数据源` 这件核心的事情, 并不限制你的具体操作, 切换了数据源可以做任何CRUD.
* 默认的数据源名称为 `master`, 你可以通过 `longfor.data.dynamic.primary` 修改.
* 方法上的注解优先于类上注解

## 用法

maven 添加依赖

```xml
<dependency>
    <groupId>com.longfor.daenerys3</groupId>
    <artifactId>daenerys3-data-mybatis</artifactId>
    <version>${daenerys3.version}</version>
</dependency>
```

根据链接池的需要, 选择使用 Druid 或者 HikariCP

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>${druid.version}</version>
</dependency>
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
</dependency>
```

application.yml 配置文件如下

```yml
longfor:
  data:
    #多数据源配置
    dynamic:
      primary: master
      datasource:
        master:
          #默认数据源
          type: com.alibaba.druid.pool.DruidDataSource
          driver-class-name: org.h2.Driver
          url: jdbc:h2:mem:siberia;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
          username: sa
          password:
          druid:
            filters: stat
            max-active: 20
            initial-size: 1
            max-wait: 30000
            min-idle: 1
            time-between-eviction-runs-millis: 60000
            min-evictable-idle-time-millis: 300000
            test-while-idle: true
            test-on-borrow: false
            test-on-return: false
            pool-prepared-statements: false
            max-open-prepared-statements: -1
            remove-abandoned: true
            remove-abandoned-timeout-millis: 500000
            web-stat-filter-exclusions: '*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/download/*,/wj/*,/assets/*'
        custom01:
          type: com.zaxxer.hikari.HikariDataSource
          driver-class-name: org.h2.Driver
          url: jdbc:h2:mem:siberia;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
          username: sa
          password:
          hikari:
            #控制从池返回的连接的默认自动提交行为。默认值：true
            auto-commit: true
            #客户端等待连接的最大毫秒数。如果在没有可用连接的情况下超过此时间，则会抛出SQLException。默认值：30000
            connection-timeout: 10000
            #此属性控制允许连接在池中闲置的最长时间。此设置仅适用于minimumIdle定义为小于maximumPoolSize。默认值：600000（10分钟）
            idle-timeout: 600000
            #此属性控制池中连接的最大生存期。默认值：1800000（30分钟）
            max-lifetime: 1800000
            #该属性控制HikariCP尝试在池中维护的最小空闲连接数。默认值：与maximumPoolSize相同
            minimum-idle: 10
            #此属性控制池允许达到的最大大小，包括空闲和正在使用的连接。默认值：10
            maximum-pool-size: 10
        custom02:
          driver-class-name: com.mysql.jdbc.Driver
          url: jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=UTF8
          username: root
          password: root
```

这里定义了 3 个数据源, 并通过 `longfor.data.dynamic.primary` 指定 master 为默认数据源.
每个数据源, 通过 `type` 来指定使用的连接池. 
更多关于连接池的配置, 参考 `DruidDataSourceProperties.java` 和 `HikariDataSourceProperties.java`.
custom02 并没有指定连接池, 系统会默认使用 druid(前提是 maven引入了 druid依赖, 如果没有引入, 则不适用连接池).

## 使用 @LFAssignDataSource 切换数据源
@LFAssignDataSource 可以注解在方法上和类上, 同时存在方法注解优先于类上注解, 强烈建议注解在 service 实现或 mapper 接口方法上.

没有 @LFAssignDataSource 则默认数据源

```java
@Service
@LFAssignDataSource("slave")
public class UserServiceImpl implements UserService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> selectAll() {
    return  jdbcTemplate.queryForList("select * from user");
  }
  
  @Override
  @LFAssignDataSource("slave_1")
  public List<Map<String, Object>> selectByCondition() {
    return  jdbcTemplate.queryForList("select * from user where age >10");
  }

}
```

在 mybatis 环境下也可注解在 mapper 接口层

```java
@LFAssignDataSource("slave")
public interface UserMapper {

  @Insert("INSERT INTO user (name,age) values (#{name},#{age})")
  boolean addUser(@Param("name") String name, @Param("age") Integer age);

  @Update("UPDATE user set name=#{name}, age=#{age} where id =#{id}")
  boolean updateUser(@Param("id") Integer id, @Param("name") String name, @Param("age") Integer age);

  @Delete("DELETE from user where id =#{id}")
  boolean deleteUser(@Param("id") Integer id);

  @Select("SELECT * FROM user")
  @LFAssignDataSource("slave_1")
  List<User> selectAll();

} 
```

