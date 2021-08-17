package com.redis.lock;

import com.redis.lock.service.RedlockService;
import org.junit.jupiter.api.Test;
import org.redisson.RedissonRedLock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * RedlockService
 *
 * @author lizhifu
 * @date 2021/8/17
 */
@SpringBootTest
public class RedlockServiceTest {
    @Resource
    private RedlockService redlockService;
    @Test
    public void test(){
        RedissonRedLock lock = redlockService.lock("locktest");
        redlockService.unLock(lock);
    }
}
