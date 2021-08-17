package com.redis.geo.service.impl;

import com.redis.geo.service.GeoService;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * GeoService
 *
 * @author lizhifu
 * @date 2021/8/17
 */
@Service
public class GeoServiceImpl implements GeoService {
    private static final String key = "USER_LOCATION:";
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addGeo(Long userId, Point point) {
        stringRedisTemplate.opsForGeo().add(key,point,userId.toString());
    }

    @Override
    public Distance distance(Long userId1, Long userId2) {
        return stringRedisTemplate.opsForGeo().distance(key, userId1.toString(), userId2.toString());
    }

    @Override
    public void range(Long userId, Distance distance) {
        // 查询某坐标点指定范围内的所有坐标点
        stringRedisTemplate.opsForGeo().radius(key,userId.toString(),distance);
        // 查询某坐标点的坐标信息
        stringRedisTemplate.opsForGeo().position(key,userId.toString());
    }
}
