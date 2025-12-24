package com.atguigu.member.generator.service;

import com.atguigu.member.generator.domain.IntegrationChangeHistory;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_integration_change_history(积分变化历史记录)】的数据库操作Service
* @createDate 2025-12-13 21:48:49
*/
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistory> {
    PageUtils queryPage(Map<String, Object> params);
}
