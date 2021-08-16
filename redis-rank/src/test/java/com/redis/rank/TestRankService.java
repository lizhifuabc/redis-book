package com.redis.rank;

import com.redis.rank.service.RankService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.Set;

/**
 * RankService
 *
 * @author lizhifu
 * @date 2021/8/16
 */
@SpringBootTest
public class TestRankService {
    @Resource
    private RankService rankService;

    @Test
    public void test(){
        System.out.println("同一用户点击100次");
//        for (int i = 0; i < 300; i++) {
//            rankService.clickHotRank(1012L);
//        }

        Set<ZSetOperations.TypedTuple<String>> set = rankService.hotRank(0,10);
        set.forEach(res->{
            System.out.println(res.getValue());
        });
    }
}
