package com.redis.lock.service;

/**
 * 锁
 *
 * @author lizhifu
 * @date 2021/8/17
 */
public interface LockService {
    /**
     * 加锁
     * @param lockKey 加锁关键字
     * @param requestId 本次请求ID
     * @return
     */
    public boolean lock(String lockKey,String requestId);

    /**
     * 释放锁
     * @param lockKey 加锁关键字
     * @param requestId 本次请求ID
     * @return
     */
    public boolean unLock(String lockKey,String requestId);
}
