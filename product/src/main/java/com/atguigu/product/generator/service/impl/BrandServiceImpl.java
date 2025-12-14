package com.atguigu.product.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.service.BrandService;
import com.atguigu.product.generator.mapper.BrandMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【pms_brand(品牌)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
    implements BrandService{

}




