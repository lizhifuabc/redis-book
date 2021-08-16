package com.redis.bitmap.service;

import java.time.LocalDate;
import java.util.List;

/**
 * UserLoginService
 *
 * @author lizhifu
 * @date 2021/8/16
 */
public interface UserLoginService {
    /**
     * 登录
     * @param userId 用户ID
     * @param localDate 登录时间
     */
    public void login(Long userId, LocalDate localDate);

    /**
     * 是否登录
     * @param userId 用户ID
     * @param localDate 时间
     * @return
     */
    public boolean isLogin(Long userId, LocalDate localDate);

    /**
     * 当月登录次数
     * @param userId 用户ID
     * @param localDate 时间
     * @return
     */
    public long countLogin(Long userId, LocalDate localDate);

    /**
     * bitop数据统计
     */
    public void bitop();

    /**
     * 连续登录
     * @param userId 用户ID
     * @param localDate 时间
     * @return
     */
    public List<Long> countContinuityLogin(Long userId, LocalDate localDate);
}
