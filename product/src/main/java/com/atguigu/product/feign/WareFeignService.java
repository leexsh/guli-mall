package com.atguigu.product.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ware")
public class WareFeignService {
}
