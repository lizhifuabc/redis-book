package com.redis.id;

import com.redis.id.service.IdGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * IdGeneratorService
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@SpringBootTest
public class IdGeneratorServiceTest {
    @Resource
    private IdGeneratorService idGeneratorService;
    @Test
    public void test(){
        System.out.println(idGeneratorService.getId());
    }

}
