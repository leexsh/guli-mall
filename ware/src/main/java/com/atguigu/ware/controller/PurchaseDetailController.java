package com.atguigu.ware.controller;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import com.atguigu.ware.generator.domain.PurchaseDetail;
import com.atguigu.ware.generator.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/ware/purchaseDetail")
public class PurchaseDetailController {

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:purchasedetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:purchasedetail:info")
    public R info(@PathVariable("id") Long id){
        PurchaseDetail purchaseDetail = purchaseDetailService.getById(id);

        return R.ok().put("purchaseDetail", purchaseDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:purchasedetail:save")
    public R save(@RequestBody PurchaseDetail purchaseDetail){
        purchaseDetailService.save(purchaseDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:purchasedetail:update")
    public R update(@RequestBody PurchaseDetail purchaseDetail){
        purchaseDetailService.updateById(purchaseDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:purchasedetail:delete")
    public R delete(@RequestBody Long[] ids){
        purchaseDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
