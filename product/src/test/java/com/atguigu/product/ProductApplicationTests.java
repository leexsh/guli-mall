package com.atguigu.product;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {
    @Autowired
    private BrandService brandService;
    @Test
    void contextLoads() {
        brandService.list().forEach(System.out::println);
    }

}
