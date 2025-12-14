package com.atguigu.ware.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.ware.generator.domain.WareInfo;
import com.atguigu.ware.generator.service.WareInfoService;
import com.atguigu.ware.generator.mapper.WareInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo>
    implements WareInfoService{

}




