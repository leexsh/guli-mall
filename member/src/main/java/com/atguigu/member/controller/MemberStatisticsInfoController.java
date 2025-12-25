package com.atguigu.member.controller;

import com.atguigu.member.generator.domain.MemberStatisticsInfo;
import com.atguigu.member.generator.service.MemberStatisticsInfoService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/member/statisticsinfo")
public class MemberStatisticsInfoController {
    @Autowired
    private MemberStatisticsInfoService memberStatisticsInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:memberstatisticsinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberStatisticsInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:memberstatisticsinfo:info")
    public R info(@PathVariable("id") Long id){
        MemberStatisticsInfo memberStatisticsInfo = memberStatisticsInfoService.getById(id);

        return R.ok().put("memberStatisticsInfo", memberStatisticsInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:memberstatisticsinfo:save")
    public R save(@RequestBody MemberStatisticsInfo memberStatisticsInfo){
        memberStatisticsInfoService.save(memberStatisticsInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:memberstatisticsinfo:update")
    public R update(@RequestBody MemberStatisticsInfo memberStatisticsInfo){
        memberStatisticsInfoService.updateById(memberStatisticsInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberstatisticsinfo:delete")
    public R delete(@RequestBody Long[] ids){
        memberStatisticsInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
