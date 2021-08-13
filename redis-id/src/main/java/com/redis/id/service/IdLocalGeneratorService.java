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
    /**
     * 一次性获取数量标记
     */
    private static Long num;
    /**
     * 一次性获取数量标记
     */
    private static Long _num = 5L;
    private Long start;
    /**
     * 获取ID
     * @return
     */
    public synchronized Long getId() {
        if(num == 0){
            increment();
        }
        num --;
        LocalDateTime now = LocalDateTime.now();
        String prefix = now.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        return Long.valueOf(prefix + String.format("%1$06d",start++));
    }

    @Override
    public void run(String... args) throws Exception {
        increment();
    }

    private void increment(){
        num = _num;
        //当前数据是50065，增长5之后的数据为50070,50070-5=50065,开始数据的使用
        start = stringRedisTemplate.opsForValue().increment(ID_KEY,_num) - _num;
    }
}
