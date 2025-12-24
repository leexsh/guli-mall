package com.atguigu.product.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.CommentReplay;
import com.atguigu.product.generator.service.CommentReplayService;
import com.atguigu.product.generator.mapper.CommentReplayMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_comment_replay(商品评价回复关系)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplay>
    implements CommentReplayService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentReplay> page = this.page(
                new Query<CommentReplay>().getPage(params),
                new QueryWrapper<CommentReplay>()
        );

        return new PageUtils(page);
    }
}




