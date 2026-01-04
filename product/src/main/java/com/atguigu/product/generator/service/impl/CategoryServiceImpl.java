package com.atguigu.product.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.product.generator.domain.Category;
import com.atguigu.product.generator.mapper.CategoryMapper;
import com.atguigu.product.generator.service.CategoryService;
import com.atguigu.product.vo.Catelogs2Vo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    CategoryBrandRelationServiceImpl categoryBrandRelationService;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Category> page = this.page(
                new Query<Category>().getPage(params),
                new QueryWrapper<Category>()
        );

        return new PageUtils(page);
    }
    @Override
    public List<Category> listWithTree() {

       List<Category> categories = baseMapper.selectList(null);
       List<Category> res = categories.stream().filter(category -> category.getParentCid() == 0)
               .map((menu)->{
                   menu.setChildren(getChildren(menu,categories));
                   return menu;
               }).sorted((m1, m2)->{
                   return (m1.getSort() == null ? 0 : m1.getSort())- (m2.getSort() == null ? 0 : m2.getSort());
               }).toList();
        return res;
    }

    @Override
    public void removeMenuByIds(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //1、收集当前节点id
        paths.add(catelogId);
        Category byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

    @Override
    public Long[] findCatalogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    @Transactional
    @Override
    public void updateCascade(Category category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    @Override
    public List<Category> getLevel1Categories() {
        System.out.println("get Level 1 Categories........");
        long l = System.currentTimeMillis();
        List<Category> categoryEntities = this.baseMapper.selectList(
                new QueryWrapper<Category>().eq("parent_cid", 0));
        System.out.println("消耗时间：" + (System.currentTimeMillis() - l));
        return categoryEntities;
    }

    @Override
    public Map<String, List<Catelogs2Vo>> getCatalogJson() {
        String catalogJSON = redisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSON)) {
            // 2. 缓存中没有，查询数据库
            Map<String, List<Catelogs2Vo>> catalogJsonFromDB = getCatalogJsonFromDB();
            // 3. 查询到的数据存放到缓存中，将对象转成 JSON 存储
            redisTemplate.opsForValue().set("catalogJSON", JSON.toJSONString(catalogJsonFromDB));
            return getCatelogJsonFromDBWithRedisLock();
        }
        return JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelogs2Vo>>>() {
        });
    }

    public Map<String, List<Catelogs2Vo>> getCatalogJsonFromDbWithRedissonLock() {
        RReadWriteLock rwlock = redissonClient.getReadWriteLock("catalogJSON-lock");
        rwlock.writeLock().lock();
        try {
            // 2. 缓存中没有，查询数据库
            Map<String, List<Catelogs2Vo>> catalogJsonFromDB = getCatalogJsonFromDB();
            // 3. 查询到的数据存放到缓存中，将对象转成 JSON 存储
            redisTemplate.opsForValue().set("catalogJSON", JSON.toJSONString(catalogJsonFromDB));
            return catalogJsonFromDB;
        } finally {
            rwlock.writeLock().unlock();
        }
    }

    public Map<String, List<Catelogs2Vo>> getCatelogJsonFromDBWithRedisLock() {
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS);
        if (lock) {
            // 2. 缓存中没有，查询数据库
            try {
                Map<String, List<Catelogs2Vo>> catalogJsonFromDB = getCatalogJsonFromDB();
                // 3. 查询到的数据存放到缓存中，将对象转成 JSON 存储
                redisTemplate.opsForValue().set("catalogJSON", JSON.toJSONString(catalogJsonFromDB));
                return catalogJsonFromDB;
            } finally {
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Collections.singletonList("lock"), uuid);
            }
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getCatelogJsonFromDBWithRedisLock();
        }
    }

    public Map<String, List<Catelogs2Vo>> getCatalogJsonFromDB() {
        return Map.of();
    }

    public List<Category> getChildren(Category category, List<Category> categories) {
        return categories.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(category.getCatId()))
                .peek(categoryEntity -> {
                    //1、找到子菜单
                    categoryEntity.setChildren(getChildren(categoryEntity, categories));
                })
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    private List<Category> getParentCid(List<Category> selectList, Long parentCid) {
        return selectList.stream().filter(item -> item.getParentCid().equals(parentCid)).collect(Collectors.toList());
    }
}




