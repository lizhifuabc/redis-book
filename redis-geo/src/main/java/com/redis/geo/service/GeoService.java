package com.redis.geo.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

/**
 * GeoService
 *
 * @author lizhifu
 * @date 2021/8/17
 */
public interface GeoService {
    /**
     * 添加位置
     * @param userId 标识
     * @param point 位置
     */
    public void addGeo(Long userId, Point point);

    /**
     * 位置举例：默认米
     * @param userId1
     * @param userId2
     * @return
     */
    public Distance distance(Long userId1,Long userId2);

    /**
     * 其他
     * @param userId
     * @param distance
     */
    public void range(Long userId,Distance distance);
}
