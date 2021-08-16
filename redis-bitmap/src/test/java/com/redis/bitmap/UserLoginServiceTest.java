package com.redis.bitmap;

import com.redis.bitmap.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * UserLoginService
 *
 * @author lizhifu
 * @date 2021/8/16
 */
@SpringBootTest
public class UserLoginServiceTest {
    @Resource
    private UserLoginService userLoginService;

    @Test
    public void test(){
        userLoginService.login(1000L,LocalDate.now());
        userLoginService.login(1000L,LocalDate.now().minusDays(1));
        userLoginService.login(1000L,LocalDate.now().minusDays(2));
        userLoginService.login(1000L,LocalDate.now().minusDays(4));
        userLoginService.login(1000L,LocalDate.now().minusDays(7));
        System.out.println(userLoginService.isLogin(1000L, LocalDate.now().minusDays(8)));
        System.out.println(userLoginService.countLogin(1000L, LocalDate.now()));
        System.out.println(userLoginService.countContinuityLogin(1000L, LocalDate.now()));

        System.out.println("数据统计");
        userLoginService.bitop();
    }
}
