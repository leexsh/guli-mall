package com.atguigu.product.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("search")
public class SearchFeignService {
}
