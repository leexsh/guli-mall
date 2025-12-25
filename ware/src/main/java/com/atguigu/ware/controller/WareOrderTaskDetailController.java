package com.atguigu.ware.controller;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import com.atguigu.ware.generator.domain.WareOrderTaskDetail;
import com.atguigu.ware.generator.service.WareOrderTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/ware/wareordertaskdetail")
public class WareOrderTaskDetailController {
    @Autowired
    private WareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:wareordertaskdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareOrderTaskDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:wareordertaskdetail:info")
    public R info(@PathVariable("id") Long id){
        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.getById(id);

        return R.ok().put("wareOrderTaskDetail", wareOrderTaskDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:wareordertaskdetail:save")
    public R save(@RequestBody WareOrderTaskDetail wareOrderTaskDetail){
        wareOrderTaskDetailService.save(wareOrderTaskDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:wareordertaskdetail:update")
    public R update(@RequestBody WareOrderTaskDetail wareOrderTaskDetail){
        wareOrderTaskDetailService.updateById(wareOrderTaskDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareordertaskdetail:delete")
    public R delete(@RequestBody Long[] ids){
        wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
