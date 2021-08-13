package com.redis.id.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局ID
 *
 * @author lizhifu
 * @date 2021/8/13
 */
@Component
@Order(value = 1)
public class IdLocalGeneratorService implements CommandLineRunner {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final String ID_KEY = "ID:GENERATOR";
    private Long key;
    /**
     * 获取ID
     * @return
     */
    public synchronized Long getId() {
        if(key == 0){
            key = stringRedisTemplate.opsForValue().increment(ID_KEY,10000);
        }
        key--;
        LocalDateTime now = LocalDateTime.now();
        String prefix = now.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        return Long.valueOf(prefix + String.format("%1$06d",key));
    }

    @Override
    public void run(String... args) throws Exception {
        key = stringRedisTemplate.opsForValue().increment(ID_KEY,10000);
    }
}
