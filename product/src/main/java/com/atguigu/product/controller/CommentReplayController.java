package com.atguigu.product.controller;


import com.atguigu.product.generator.domain.CommentReplay;
import com.atguigu.product.generator.service.CommentReplayService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/product/commentreplay")
public class CommentReplayController {
    @Autowired
    CommentReplayService commentReplayService;

    @RequestMapping("/list")
    //@RequiresPermissions("product:commentreplay:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentReplayService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:commentreplay:info")
    public R info(@PathVariable("id") Long id){
        CommentReplay commentReplay = commentReplayService.getById(id);

        return R.ok().put("commentReplay", commentReplay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:commentreplay:save")
    public R save(@RequestBody CommentReplay commentReplay){
        commentReplayService.save(commentReplay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:commentreplay:update")
    public R update(@RequestBody CommentReplay commentReplay){
        commentReplayService.updateById(commentReplay);

        return R.ok();
    }

    @RequestMapping("/delete")
    //@RequiresPermissions("product:commentreplay:delete")
    public R delete(@RequestBody Long[] ids){
        commentReplayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
