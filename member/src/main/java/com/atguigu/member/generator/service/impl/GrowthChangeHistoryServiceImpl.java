package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.GrowthChangeHistory;
import com.atguigu.member.generator.mapper.GrowthChangeHistoryMapper;
import com.atguigu.member.generator.service.GrowthChangeHistoryService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_growth_change_history(成长值变化历史记录)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryMapper, GrowthChangeHistory>
    implements GrowthChangeHistoryService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrowthChangeHistory> page = this.page(
                new Query<GrowthChangeHistory>().getPage(params),
                new QueryWrapper<GrowthChangeHistory>()
        );

        return new PageUtils(page);
    }
}




