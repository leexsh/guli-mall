package com.atguigu.ware.generator.service;

import com.atguigu.ware.generator.domain.WareOrderTaskDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhenglee
* @description 针对表【wms_ware_order_task_detail(库存工作单)】的数据库操作Service
* @createDate 2025-12-13 21:59:22
*/
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetail> {
    PageUtils queryPage(Map<String, Object> params);
}
