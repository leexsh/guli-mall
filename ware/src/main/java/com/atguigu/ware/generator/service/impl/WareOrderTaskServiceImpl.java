package com.atguigu.ware.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.ware.generator.domain.WareOrderTask;
import com.atguigu.ware.generator.mapper.WareOrderTaskMapper;
import com.atguigu.ware.generator.service.WareOrderTaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_ware_order_task(库存工作单)】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskMapper, WareOrderTask>
    implements WareOrderTaskService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTask> page = this.page(
                new Query<WareOrderTask>().getPage(params),
                new QueryWrapper<WareOrderTask>()
        );

        return new PageUtils(page);
    }
}




