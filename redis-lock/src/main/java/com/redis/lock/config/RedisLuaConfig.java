package com.redis.lock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * redis lua配置
 *
 * @author lizhifu
 * @date 2021/8/17
 */
@Configuration
public class RedisLuaConfig {
    @Bean
    public DefaultRedisScript<Long> unlockScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/unlock.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}
