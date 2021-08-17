package com.redis.lock;

import com.redis.lock.service.LockService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * LockService
 *
 * @author lizhifu
 * @date 2021/8/17
 */
@SpringBootTest
public class LockServiceTest {
    @Resource
    private LockService lockService;

    @Test
    public void test(){
        System.out.println(lockService.lock("locktest","abcd"));
        lockService.unLock("locktest","abcd");
    }
}
