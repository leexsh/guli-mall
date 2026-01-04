package com.atguigu.product;

import com.atguigu.product.generator.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class ProductApplicationTests {
    @Autowired
    private BrandService brandService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void contextLoads() {
        brandService.list().forEach(System.out::println);
    }

}
