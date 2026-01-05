package com.atguigu.seckill.service;

import com.atguigu.seckill.to.SeckillSkuRedis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeckillService {
    void upSeckillSkuLatest3Days();

    List<SeckillSkuRedis> getCurrentSeckillSkus();

    SeckillSkuRedis getSkuSeckillInfo(Long skuId);

    String kill(String killId, String key, Integer num);
}
