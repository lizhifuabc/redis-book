package com.redis.rank.service.impl;

import com.redis.rank.service.RankService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * 排行榜
 *
 * @author lizhifu
 * @date 2021/8/16
 */
@Service
public class RankServiceImpl implements RankService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void clickHotRank(Long dataId) {
        //点击热榜:Redis 有序集合(sorted set)
        //热榜key：hotRank:20210816
        //数据dataId：1000
        //命令：zincrby hotRank:20210816 1 1000
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = LocalDate.now().format(formatter);
        String key = "hotRank:" + now;
        stringRedisTemplate.opsForZSet().incrementScore(key, dataId.toString(), 1);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> hotRank(long start, long end) {
        //zrevrange hotRank:20210816 0 15 withscores
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = LocalDate.now().format(formatter);
        String key = "hotRank:" + now;
        Set<ZSetOperations.TypedTuple<String>> result = stringRedisTemplate.opsForZSet().reverseRangeWithScores(key,start,end);
        return result;
    }
}
