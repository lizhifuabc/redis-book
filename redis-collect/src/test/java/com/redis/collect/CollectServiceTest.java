package com.redis.collect;

import com.redis.collect.service.CollectService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * CollectService
 *
 * @author lizhifu
 * @date 2021/8/16
 */
@SpringBootTest
public class CollectServiceTest {
    @Resource
    private CollectService collectService;
    @Test
    public void test(){
        Long userId = 100L;
        Long dataId = 100L;

        collectService.collect(userId,dataId);
        System.out.println(collectService.isCollect(userId,dataId));
        System.out.println(collectService.countCollect(dataId));
        System.out.println(collectService.userCollect(dataId));
        collectService.unCollect(userId,dataId);
    }
}
