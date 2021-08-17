package com.redis.lock.service.impl;

import com.redis.lock.service.LockService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 锁
 *
 * @author lizhifu
 * @date 2021/8/17
 */
@Service
public class LockServiceImpl implements LockService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private DefaultRedisScript<Long> unlockScript;
    @Override
    public boolean lock(String lockKey, String requestId) {
        //失效时间 SECONDS
        long expireTime = 10;
        return stringRedisTemplate.opsForValue().setIfAbsent(lockKey,requestId,expireTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean unLock(String lockKey, String requestId) {
        //此时需要使用LUA的方式去删除锁，防止误删
        List<String> keys = Arrays.asList(lockKey);
        Long result = stringRedisTemplate.execute(unlockScript,keys,requestId);
        return false;
    }
}
