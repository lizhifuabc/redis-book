package com.redis.geo;

import com.redis.geo.service.GeoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import javax.annotation.Resource;

/**
 * GeoService
 *
 * @author lizhifu
 * @date 2021/8/17
 */
@SpringBootTest
public class GeoServiceTest {
    @Resource
    private GeoService geoService;

    @Test
    public void test(){
        // 北京
        Point point = new Point(116.23128,40.22077);
        geoService.addGeo(100L,point);
        // 上海
        point = new Point(121.48941,31.40527);
        geoService.addGeo(200L,point);
        Distance distance = geoService.distance(100L,200L);
        System.out.println(distance.toString());
    }
}
