package com.atguigu.ware.generator.service;

import com.atguigu.utils.PageUtils;
import com.atguigu.ware.generator.domain.PurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_purchase_detail】的数据库操作Service
* @createDate 2025-12-13 21:59:22
*/
public interface PurchaseDetailService extends IService<PurchaseDetail> {
    PageUtils queryPage(Map<String, Object> params);
    List<PurchaseDetail> listDetailByPurchaseId(Long id);


}
