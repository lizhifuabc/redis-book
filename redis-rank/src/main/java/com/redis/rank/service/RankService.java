package com.redis.rank.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * 排行榜
 *
 * @author lizhifu
 * @date 2021/8/16
 */
public interface RankService {
    /**
     * 点击热榜
     * @param dataId 点击数据ID
     */
    public void clickHotRank(Long dataId);

    /**
     * 获取热榜数据
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> hotRank(long start, long end);
}
