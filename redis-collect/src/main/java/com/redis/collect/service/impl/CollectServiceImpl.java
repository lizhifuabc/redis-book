package com.redis.collect.service.impl;

import com.redis.collect.service.CollectService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * CollectService
 *
 * @author lizhifu
 * @date 2021/8/16
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final String pre = "collect:";
    @Override
    public void collect(Long userId, Long dataId) {
        stringRedisTemplate.opsForSet().add(pre + dataId,userId.toString());
    }

    @Override
    public void unCollect(Long userId, Long dataId) {
        stringRedisTemplate.opsForSet().remove(pre + dataId,userId.toString());
    }

    @Override
    public boolean isCollect(Long userId, Long dataId) {
        return stringRedisTemplate.opsForSet().isMember(pre + dataId,userId.toString());
    }

    @Override
    public Set userCollect(Long dataId) {
        Set set = stringRedisTemplate.opsForSet().members(pre + dataId);
        return set;
    }

    @Override
    public Long countCollect(Long dataId) {
        return stringRedisTemplate.opsForSet().size(pre + dataId);
    }
}
