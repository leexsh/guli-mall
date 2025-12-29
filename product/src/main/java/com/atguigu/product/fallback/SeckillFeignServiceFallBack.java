package com.atguigu.product.fallback;

import com.atguigu.exception.BizCodeEnum;
import com.atguigu.product.feign.SeckillFeignService;
import com.atguigu.utils.R;

public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        BizCodeEnum BizCodeEnume;
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(),BizCodeEnum.TO_MANY_REQUEST.getMsg());
    }
}
