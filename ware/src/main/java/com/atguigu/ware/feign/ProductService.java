package com.atguigu.ware.feign;

import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product")
public interface ProductService {
    @RequestMapping("/product/skuinfo/info")
    R info(@RequestParam("skuId") Long skuId);
}
