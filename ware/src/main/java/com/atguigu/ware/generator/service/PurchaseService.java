package com.atguigu.ware.generator.service;

import com.atguigu.utils.PageUtils;
import com.atguigu.ware.generator.domain.Purchase;
import com.atguigu.ware.vo.MergeVo;
import com.atguigu.ware.vo.PurchaseDoneVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_purchase(采购信息)】的数据库操作Service
* @createDate 2025-12-13 21:59:22
*/
public interface PurchaseService extends IService<Purchase> {
    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageUnreceivePurchase(Map<String, Object> params);


    void mergePurchase(MergeVo mergeVo);


    void received(List<Long> ids);


    void done(PurchaseDoneVo doneVo);

}
