package com.redis.id;

import com.redis.id.service.IdLocalGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * IdLocalGeneratorService
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@SpringBootTest
public class IdLocalGeneratorServiceTest {
    @Resource
    private IdLocalGeneratorService idLocalGeneratorService;

    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            System.out.println(idLocalGeneratorService.getId());
        }
    }
}
