package com.redis.bitmap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * 测试offset
 *
 * @author lizhifu
 * @date 2021/8/16
 */
@SpringBootTest
public class TestOffset {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        String a = "a";
        System.out.println("a的ASCII码值为97，97的二进制："+Integer.toBinaryString(97));
        String b = "b";
        System.out.println("b的ASCII码值为98，98的二进制："+Integer.toBinaryString(98));
        stringRedisTemplate.opsForValue().set("TestOffset",a);
        System.out.println("移位前："+stringRedisTemplate.opsForValue().get("TestOffset"));
        System.out.println("开始对TestOffset进行移位操作,让值变成a-->b");
        //key对应的值value对应的ascii码,在offset的位置(从左向右数)变为value。
        //a的ASCII码值为97，97的二进制：1100001
        //b的ASCII码值为98，98的二进制：1100010
        stringRedisTemplate.opsForValue().setBit("TestOffset",6,true);
        stringRedisTemplate.opsForValue().setBit("TestOffset",7,false);
        System.out.println("移位后："+stringRedisTemplate.opsForValue().get("TestOffset"));

        System.out.println("获取偏移量上的位(bit)："+stringRedisTemplate.opsForValue().getBit("TestOffset",2));
    }
}
