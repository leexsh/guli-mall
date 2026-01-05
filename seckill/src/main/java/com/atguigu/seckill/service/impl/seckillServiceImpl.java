package com.atguigu.seckill.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.seckill.feign.CouponFeignService;
import com.atguigu.seckill.feign.ProductFeignService;
import com.atguigu.seckill.service.SeckillService;
import com.atguigu.seckill.to.SeckillSkuRedis;
import com.atguigu.seckill.vo.SeckillSessionsWithSkus;
import com.atguigu.to.SeckillOrderTo;
import com.atguigu.utils.R;
import com.atguigu.vo.MemberRespVo;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import jakarta.annotation.Resource;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class seckillServiceImpl implements SeckillService {
    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";
    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//+商品随机码

    @Resource
    CouponFeignService couponFeignService;
    @Resource
    ProductFeignService productFeignService;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    RedissonClient redissonClient;

    @Override
    public void upSeckillSkuLatest3Days() {
        R session = couponFeignService.getLates3DaySession();
        if (session.getCode() == 0) {
            //上架商品
            List<SeckillSessionsWithSkus> sessionData = session.getData(new TypeReference<List<SeckillSessionsWithSkus>>() {
            });
            //缓存到redis
            //1.缓存活动信息
            saveSessionInfos(sessionData);
            //2.缓存活动的关联商品信息
            saveSessionSkuInfos(sessionData);
        }
    }

    private void saveSessionInfos(List<SeckillSessionsWithSkus> sessions) {
        if(sessions!=null)
            sessions.stream().forEach(session -> {
                Long startTime = session.getStartTime().getTime();
                Long endTime = session.getEndTime().getTime();
                String key = SESSIONS_CACHE_PREFIX + startTime + "_" + endTime;
                Boolean hasKey = stringRedisTemplate.hasKey(key);
                if (!hasKey) {
                    //因为是stringRedisTemplate，所有返回类型为String
                    List<String> collect = session.getRelationSkus().stream().map(
                            item -> item.getPromotionSessionId() + "_" + item.getSkuId().toString()).collect(Collectors.toList());
                    stringRedisTemplate.opsForList().leftPushAll(key, collect);
                }
            });
    }

    private void saveSessionSkuInfos(List<SeckillSessionsWithSkus> sessions) {
        BoundHashOperations<String, Object, Object> ops = stringRedisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        if (sessions!=null)
            sessions.stream().forEach(session -> {
                //1.随机码(防止攻击)
                String token = UUID.randomUUID().toString().replace("-", "");
                session.getRelationSkus().stream().forEach(seckillSkuVo -> {
                    if (!ops.hasKey(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString())) {
                        //缓存商品hash结构
                        SeckillSkuRedis redisTo = new SeckillSkuRedis();
                        //2.缓存秒杀商品信息(sku信息)
                        R skuInfo = productFeignService.getSkuInfo(seckillSkuVo.getSkuId());
                        if (skuInfo.getCode() == 0) {
//                            SkuInfoVo info = skuInfo.getData2("skuInfo", new TypeReference<SkuInfoVo>() {
//                            });
//                            redisTo.setSkuInfoVo(info);
                        }
                        //3.缓存秒杀商品基本信息
                        BeanUtils.copyProperties(seckillSkuVo, redisTo);
                        //4.设置当前商品的秒杀时间信息
                        redisTo.setStartTime(session.getStartTime().getTime());
                        redisTo.setEndTime(session.getEndTime().getTime());
                        redisTo.setRandomCode(token);
                        String jsonString = JSON.toJSONString(redisTo);
                        ops.put(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString(), jsonString);
                        //如果当前场次的商品库存信息已经上架，就不需要再上架
                        //5.引入分布式信号量(限流)
                        RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                        //商品可以秒杀的数量作为信号量
                        semaphore.trySetPermits(seckillSkuVo.getSeckillCount());
                    }
                });
            });
    }
    @Override
    public List<SeckillSkuRedis> getCurrentSeckillSkus() {
        return List.of();
    }

    @Override
    public SeckillSkuRedis getSkuSeckillInfo(Long skuId) {
        return null;
    }

    @Override
    public String kill(String killId, String key, Integer num) {
        MemberRespVo respVo = null;
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        String json = ops.get(killId);
        if (StringUtils.isEmpty(json)){
            return null;
        }else {
            //2.判断合法性校验（拦截器已判断是否登录)
            SeckillSkuRedis redisTo = JSON.parseObject(json, SeckillSkuRedis.class);
            //2.1校验时间合法性
            Long startTime = redisTo.getStartTime();
            Long endTime = redisTo.getEndTime();
            long time = new Date().getTime();
            long ttl = endTime - time;//毫秒
            if(time >= startTime && time <= endTime){
                //2.2校验随机码和商品id
                String randomCode = redisTo.getRandomCode();
                String id = redisTo.getPromotionSessionId()+"_"+redisTo.getSkuId();
                if(randomCode.equals(key) && killId.equals(id)){
                    //2.3验证购买数量
                    if(num <= redisTo.getSeckillLimit()){
                        //2.4验证是否购买过商品(幂等性)
                        //setnx不存在的时候才占位
                        String redisKey = respVo.getId()+"_"+id;
                        //自动过期
                        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(redisKey, num.toString(), ttl, TimeUnit.MILLISECONDS);
                        if(aBoolean){
                            //成功表示用户第一次购买
                            //3.获取信号量tryAcquire()非阻塞方法
                            RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + randomCode);
                            boolean b = semaphore.tryAcquire(num);//获取信号量
                            if(b){
                                //秒杀成功，快速下单,返回订单号
                                String timeId = IdWorker.getTimeId();
                                //4.向队列发送消息
                                SeckillOrderTo orderTo = new SeckillOrderTo();
                                orderTo.setOrderSn(timeId);
                                orderTo.setMemberId(respVo.getId());
                                orderTo.setNum(num);
                                orderTo.setPromotionSessionId(redisTo.getPromotionSessionId());
                                orderTo.setSkuId(redisTo.getSkuId());
                                orderTo.setSeckillPrice(redisTo.getSeckillPrice());
//                                rabbitTemplate.convertAndSend("order-event-exchange","order.seckill.order",orderTo);
                                return timeId;
                            }
                            return null;
                        }else{
                            //用户已经购买
                            return null;
                        }
                    }
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }
        return null;
    }
}
