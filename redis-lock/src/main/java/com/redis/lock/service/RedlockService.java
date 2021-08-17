package com.redis.lock.service;

import org.redisson.RedissonRedLock;

/**
 * Redlock
 *
 * @author lizhifu
 * @date 2021/8/17
 */
public interface RedlockService {
    /**
     * 加锁
     * @param lockKey 加锁关键字
     * @return
     */
    public RedissonRedLock lock(String lockKey);

    /**
     * 释放锁
     * @param lock lock
     * @return
     */
    public void unLock(RedissonRedLock lock);
}
