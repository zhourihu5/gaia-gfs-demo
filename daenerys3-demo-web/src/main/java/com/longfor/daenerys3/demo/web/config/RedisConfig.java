package com.longfor.daenerys3.demo.web.config;

import com.longfor.daenerys3.data.redis.DynamicRedisProvider;
import com.longfor.daenerys3.data.redis.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author shanhonghao
 * @since 2018-08-31 16:14
 */
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
