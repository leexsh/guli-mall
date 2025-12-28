package com.atguigu.mallsearch.openfeign;

@FeignClient("mall-product")
public interface ProductFeign {
    @GetMapping("/product/attr/info/{attrId}")
    R attrInfo(@PathVariable("attrId") Long attrId);

}
