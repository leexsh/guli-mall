package com.atguigu.ware.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.ware.generator.domain.Purchase;
import com.atguigu.ware.generator.service.PurchaseService;
import com.atguigu.ware.generator.mapper.PurchaseMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【wms_purchase(采购信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{

}




