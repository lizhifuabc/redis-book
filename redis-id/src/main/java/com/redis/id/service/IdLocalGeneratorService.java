package com.redis.id.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 全局ID
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@Service
public class IdLocalGeneratorService{
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final String ID_KEY = "ID:GENERATOR";
    /**
     * 下一个ID
     */
    private long nextId = 0;

    /**
     * 最大ID
     */
    private long maxId = 0;
    /**
     * 缓存数量
     */
    private int cacheSize = 1;
    /**
     * 获取ID
     * @return
     */
    public synchronized long getId() {
        if (this.maxId == this.nextId) {
            this.maxId = increment();
            this.nextId = this.maxId - cacheSize + 1;
        }else {
            this.nextId++;
        }
        return this.nextId;
    }
    private long increment(){
        return stringRedisTemplate.opsForValue().increment(ID_KEY,cacheSize);
    }
}
