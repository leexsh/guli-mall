package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.IntegrationChangeHistory;
import com.atguigu.member.generator.mapper.IntegrationChangeHistoryMapper;
import com.atguigu.member.generator.service.IntegrationChangeHistoryService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_integration_change_history(积分变化历史记录)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryMapper, IntegrationChangeHistory>
    implements IntegrationChangeHistoryService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IntegrationChangeHistory> page = this.page(
                new Query<IntegrationChangeHistory>().getPage(params),
                new QueryWrapper<IntegrationChangeHistory>()
        );

        return new PageUtils(page);
    }
}




