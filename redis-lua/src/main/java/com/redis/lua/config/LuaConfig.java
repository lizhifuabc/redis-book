package com.redis.lua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * LUA配置
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@Configuration
public class LuaConfig {
    @Bean
    public DefaultRedisScript<String> stringScript() {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/string.lua")));
        redisScript.setResultType(String.class);
        return redisScript;
    }

    @Bean
    public DefaultRedisScript<Integer> integerScript() {
        DefaultRedisScript<Integer> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/string.lua")));
        redisScript.setResultType(Integer.class);
        return redisScript;
    }

    /**
     * Redis中的integer对应Java中的Long
     * @return
     */
    @Bean
    public DefaultRedisScript<Long> integerScript2() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/integer.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    @Bean
    public DefaultRedisScript<List> listScript() {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/list.lua")));
        redisScript.setResultType(List.class);
        return redisScript;
    }
}
