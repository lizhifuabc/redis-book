package com.redis.bitmap.service.impl;

import com.redis.bitmap.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * UserLoginService
 *
 * @author lizhifu
 * @date 2021/8/16
 */
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final String pre = "userLoginLog:";
    @Override
    public void login(Long userId, LocalDate localDate) {
        // 用户一天有没有登录只有两种状态：是和否（1，0）
        // 一个月最多31天
        // 使用4个byte32位表示一个人一个月的登录状态
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String now = localDate.format(formatter);
        //前缀:时间:用户ID
        String key = pre + now + ":" + userId;
        log.info("UserLoginService key is {}",key);
        //setbit KEY_NAME OFFSET
        stringRedisTemplate.opsForValue().setBit(key,localDate.getDayOfMonth(),true);
    }

    @Override
    public boolean isLogin(Long userId, LocalDate localDate) {
        //可以判断月是否登录
        //可以判断日是否登录
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String now = localDate.format(formatter);
        //前缀:时间:用户ID
        String key = pre + now + ":" + userId;
        log.info("UserLoginService key is {}",key);
        return stringRedisTemplate.opsForValue().getBit(key,localDate.getDayOfMonth());
    }

    @Override
    public long countLogin(Long userId, LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String now = localDate.format(formatter);
        //前缀:时间:用户ID
        String key = pre + now + ":" + userId;
        log.info("UserLoginService key is {}",key);
        long res = stringRedisTemplate.execute((RedisCallback<Long>) conn ->(
            conn.bitCount(key.getBytes())
        ));
        return res;
    }

    @Override
    public void bitop() {
        // bitop(operation, dest, *keys)
        // operation - AND（并） 、 OR（或） 、 NOT（非） 、 XOR（异或）
        // destination - 新的Redis的name
        // *keys - 要查找的Redis的name
        stringRedisTemplate.execute((RedisCallback<Long>) conn ->(
                conn.bitOp(RedisStringCommands.BitOperation.OR,"bitopresult".getBytes(),"userLoginLog:202108:3000".getBytes(),"userLoginLog:202108:3000".getBytes())
        ));

        log.info("bitop {}",stringRedisTemplate.opsForValue().get("bitopresult"));
    }

    @Override
    public List<Long> countContinuityLogin(Long userId, LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String now = localDate.format(formatter);
        //前缀:时间:用户ID
        String key = pre + now + ":" + userId;
        log.info("UserLoginService key is {}",key);
        //例如，今天是2021年08月16日
        //BITFIELD命令能操作多字节位域，它会执行一系列操作，并返回一个响应数组，在参数列表中每个响应数组匹配相应的操作
        //获取从某个位到某个位的值
        List<Long> res = stringRedisTemplate.execute((RedisCallback<List<Long>>) conn ->(
                conn.bitField(key.getBytes(),
                        BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(localDate.getDayOfMonth())).valueAt(0))
        ));
        return res;
    }
}
