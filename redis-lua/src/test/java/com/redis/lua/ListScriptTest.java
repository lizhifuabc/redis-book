package com.redis.lua;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 返回list测试
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@SpringBootTest
public class ListScriptTest {
    @Resource
    private DefaultRedisScript<List> listScript;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        //del com.redis.lua
        String key = "com.redis.lua";
        List<String> valueList = new ArrayList<>();
        valueList.add("1");
        valueList.add("2");
        //设置一个数值
        stringRedisTemplate.opsForList().rightPushAll(key, valueList);
        //通过lua获取该值
        List<String> keys = Arrays.asList(key);
        //LRANGE com.redis.lua 0 -1
        List result = stringRedisTemplate.execute(listScript, keys);
        System.out.println("通过lua获取该值:"+result);
    }
}
