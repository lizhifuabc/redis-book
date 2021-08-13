package com.redis.lua;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * IntegerScriptTest
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@SpringBootTest
public class IntegerScriptTest {
    @Resource
    private DefaultRedisScript<Integer> integerScript;
    @Resource
    private DefaultRedisScript<Long> integerScript2;
    @Resource
    private RedisTemplate<String,Integer> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        String key = "com.redis.lua";
        //设置一个数值
        redisTemplate.opsForValue().set(key,1);
        //通过lua获取该值
        List<String> keys = Arrays.asList(key);
        Integer result = redisTemplate.execute(integerScript, keys);
        System.out.println("通过lua获取该值:"+result);
    }
    @Test
    public void testString(){
        String key = "com.redis.lua";
        //设置一个数值
        stringRedisTemplate.opsForValue().set(key,"1");
        //通过lua获取该值
        List<String> keys = Arrays.asList(key);
        Long result = stringRedisTemplate.execute(integerScript2, keys);
        System.out.println("通过lua获取该值:"+result);
    }
}
