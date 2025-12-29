package com.atguigu.product.feign;

import com.atguigu.to.SkuReductionTo;
import com.atguigu.to.SpuBound;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("coupon")
public interface CouponFeignService {
    @PostMapping("/coupon/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBound spuBoundsTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
