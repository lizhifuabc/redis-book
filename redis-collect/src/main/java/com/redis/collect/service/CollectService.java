package com.redis.collect.service;

import java.util.List;
import java.util.Set;

/**
 * CollectService
 *
 * @author lizhifu
 * @date 2021/8/16
 */
public interface CollectService {
    /**
     * 收藏
     * @param userId 用户ID
     * @param dataId 数据ID
     */
    public void collect(Long userId,Long dataId);

    /**
     * 取消收藏
     * @param userId 用户ID
     * @param dataId 数据ID
     */
    public void unCollect(Long userId,Long dataId);

    /**
     * 是否收藏
     * @param userId 用户ID
     * @param dataId 数据ID
     */
    public boolean isCollect(Long userId,Long dataId);

    /**
     * 所有收藏用户
     * @param dataId 数据ID
     */
    public Set userCollect(Long dataId);

    /**
     * 收藏总数
     * @param dataId 数据ID
     */
    public Long countCollect(Long dataId);
}
