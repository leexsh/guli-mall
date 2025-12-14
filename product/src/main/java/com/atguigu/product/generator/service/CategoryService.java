package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.Category;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
@Service
public interface CategoryService extends IService<Category> {
    PageUtils queryPage(Map<String, Object> params);
    List<Category> listWithTree();
}
