package com.redis.rank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TestLocalDate
 *
 * @author lizhifu
 * @date 2021/8/16
 */
public class TestLocalDate {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(LocalDate.now().format(formatter));
    }
}
