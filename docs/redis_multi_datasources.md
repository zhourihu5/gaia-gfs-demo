# redis 多数据源

---

支持更方便的使用redis服务.
之所以没有采用spring给的 template 方案, 是感觉整体过重, 不够灵活.
kcb-data-redis 在直接提供 jedis 作为直接操作的对象以保证最大灵活性的基础上, 解决了连接池的资源申请与释放工作.

## 用法

maven pom.xml 中添加依赖

```xml
<dependency>
    <groupId>com.longfor.daenerys3</groupId>
    <artifactId>daenerys3-data-redis</artifactId>
    <version>${daenerys3.version}</version>
</dependency>
``` 

需要开发人员自己配置JedisPool 或 JedisSentinelPool

```yaml
longfor.data.dynamic.redis:
  demoSentinel:
    database: 10
    minIdle: 50
    maxTotal: 50
    mastername: upp_redis
    timeout: 2000
    sentinel: 10.214.168.103:26380,10.214.168.104:26380,10.214.168.105:26380
  demoRedis:
    database: 10
    minIdle: 50
    maxTotal: 50
    timeout: 2000
    host: 127.0.0.1
    port: 3389
```

调用方法如下
```java

@Resource
private Pool<Jedis> demoSentinel;
@Resource
private Pool<Jedis> demoRedis;

String value = RedisHelper.doRedis(jedis -> jedis.get("key"), demoRedis);
```

