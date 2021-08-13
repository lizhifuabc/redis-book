package com.redis.lua;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * 客户端测试
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@SpringBootTest
public class StringRedisTemplateTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        //字符串操作
        Integer res = stringRedisTemplate.opsForValue().append("com.redis.lua","com.redis.lua");
        System.out.println(stringRedisTemplate.opsForValue().get("com.redis.lua"));
        System.out.println(stringRedisTemplate.delete("com.redis.lua"));
    }
}
