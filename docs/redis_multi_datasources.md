# redis 多数据源

---

支持更方便的使用redis服务.

## 用法

maven pom.xml 中添加依赖

```xml
<dependency>
    <groupId>com.longfor.gfs</groupId>
    <artifactId>gfs-data-redis</artifactId>
    <version>${gfs.version}</version>
</dependency>
``` 

配置文件中加入如下信息

```yaml
longfor:
  data:
    dynamic:
      redis:      
       connection:
         demoRedis:
           database: 10
           timeout: 2000
           host: 127.0.0.1
           port: 6379
           pool:
             minIdle: 50
             maxActive: 50
```

更多的配置信息, 请参考 `org.springframework.boot.autoconfigure.data.redis.RedisProperties`.

还需要创建一个 RedicConfig.java

```java
@Configuration
public class RedisConfig {

    @Resource
    private DynamicRedisProvider dynamicRedisProvider;

    @Bean(name = "demoRedis")
    public RedisTemplate demoRedis() {
        StringRedisTemplate template = new StringRedisTemplate(dynamicRedisProvider.loadRedis().get("demoRedis"));
        JacksonSerializer.setJacksonSerializer(template);
        return template;
    }
}
```

通过 beanName 我们可以支持多个 redis 数据源.

我们定义了一组redis 的 jackson 序列化方案, 可以直接通过 JacksonSerializer.setJacksonSerializer 配置.

```java
public class JacksonSerializer {
    public static void setJacksonSerializer(RedisTemplate<String, String> template) {
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }
}
```

调用方法如下
```java

@Resource
private RedisTemplate demoRedis;

private Env loadInCache(String redisKey) {
    try {
        return (Env) demoRedis.opsForValue().get(redisKey);
    } catch (JedisConnectionException e) {
        log.warn("Connect redis failed. {}", e.getMessage());
        return null;
    }
}

private void cacheEnv(String redisKey, Env env) {
    demoRedis.opsForValue().set(redisKey, env);
}
```
