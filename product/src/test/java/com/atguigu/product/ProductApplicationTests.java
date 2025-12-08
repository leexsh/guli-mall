package com.atguigu.product;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {
    @Autowired
    private BrandService brandService;
    @Test
    void contextLoads() {
        Brand brand = new Brand();
        brand.setName("华为");
        brandService.save(brand);
        System.out.println("save success");
    }

}
