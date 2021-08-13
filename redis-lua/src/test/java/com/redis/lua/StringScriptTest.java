package com.redis.lua;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 返回string测试
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@SpringBootTest
public class StringScriptTest {
    @Resource
    private DefaultRedisScript<String> stringScript;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        String key = "com.redis.lua";
        //设置一个数值
        stringRedisTemplate.opsForValue().set(key,"com.redis.lua");
        //通过lua获取该值
        List<String> keys = Arrays.asList(key);
        String result = stringRedisTemplate.execute(stringScript, keys);
        System.out.println("通过lua获取该值:"+result);
    }
}
