package com.redis.lock.service.impl;

import com.redis.lock.service.RedlockService;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * RedlockService
 *
 * @author lizhifu
 * @date 2021/8/17
 */
@Service
public class RedlockServiceImpl implements RedlockService {
    @Override
    public RedissonRedLock lock(String lockKey) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        RedissonClient redissonRed1 = Redisson.create(config);
        RLock rLock1 = redissonRed1.getLock(lockKey);

        RedissonRedLock lock = new RedissonRedLock(rLock1);

        // 给lock1，lock2，lock3加锁，如果没有手动解开的话，10秒钟后将会自动解开
        lock.lock(10, TimeUnit.SECONDS);
        // 为加锁等待100秒时间，并在加锁成功10秒钟后自动解开
        // boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        return lock;
    }

    @Override
    public void unLock(RedissonRedLock lock) {
        lock.unlock();
    }
}
