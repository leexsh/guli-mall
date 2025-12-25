package com.atguigu.ware.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.ware.generator.domain.WareInfo;
import com.atguigu.ware.generator.mapper.WareInfoMapper;
import com.atguigu.ware.generator.service.WareInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo>
    implements WareInfoService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfo> wareInfoEntityQueryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wareInfoEntityQueryWrapper.eq("id",key).or()
                    .like("name",key)
                    .or().like("address",key)
                    .or().like("areacode",key);
        }

        IPage<WareInfo> page = this.page(
                new Query<WareInfo>().getPage(params),
                wareInfoEntityQueryWrapper
        );

        return new PageUtils(page);
    }
}




