package com.redis.id.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局ID
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@Service
public class IdGeneratorService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final String ID_KEY = "ID:GENERATOR";

    /**
     * 获取ID
     * @return
     */
    public Long getId() {
        LocalDateTime now = LocalDateTime.now();
        String prefix = now.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        Long key = stringRedisTemplate.opsForValue().increment(ID_KEY);
        return Long.valueOf(prefix + String.format("%1$06d",key));
    }
}
