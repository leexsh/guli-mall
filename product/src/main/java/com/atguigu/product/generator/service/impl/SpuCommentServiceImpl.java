package com.atguigu.product.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.SpuComment;
import com.atguigu.product.generator.service.SpuCommentService;
import com.atguigu.product.generator.mapper.SpuCommentMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_spu_comment(商品评价)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:03
*/
@Service
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentMapper, SpuComment>
    implements SpuCommentService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuComment> page = this.page(
                new Query<SpuComment>().getPage(params),
                new QueryWrapper<SpuComment>()
        );

        return new PageUtils(page);
    }
}




