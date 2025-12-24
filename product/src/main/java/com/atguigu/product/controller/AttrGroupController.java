package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.Attr;
import com.atguigu.product.generator.domain.AttrAttrgroupRelation;
import com.atguigu.product.generator.domain.AttrGroup;
import com.atguigu.product.generator.service.AttrAttrgroupRelationService;
import com.atguigu.product.generator.service.AttrGroupService;
import com.atguigu.product.generator.service.AttrService;
import com.atguigu.product.generator.service.CategoryService;
import com.atguigu.product.vo.AttrGroupRelationVo;
import com.atguigu.product.vo.AttrGroupWithAttrsVo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    private AttrService attrService;


    @RequestMapping("/attr/relation")
    //@RequiresPermissions("product:attrattrgrouprelation:save")
    public R saveRelation(@RequestBody List<AttrGroupRelationVo> collections) {
        attrAttrgroupRelationService.saveBatch(collections);
        return R.ok();
    }

    @GetMapping("/{catelogId}/withattr")
    //@RequiresPermissions("product:attrattrgrouprelation:list")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId) {
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data", vos);
    }

    @GetMapping("/{attrgroupId}/attr/relation")
    //@RequiresPermissions("product:attrattrgrouprelation:list")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<Attr> entities = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data", entities);
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R noAttrRelation(@PathVariable("attrgroupId") Long attrgroupId, @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);
        return R.ok().put("data", page);
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R deleteNoRelation(@RequestBody AttrGroupRelationVo[] vos) {
        attrService.deleteRelation(vos);
        return R.ok();
    }

    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }

    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroup attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatalogPath(catelogId);

        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    @RequestMapping("/save")
    //@RequiresPermissions("product:attrattrgrouprelation:save")
    public R save(@RequestBody AttrGroup attrgroup) {
        attrGroupService.save(attrgroup);
        return R.ok();
    }

    @RequestMapping("/update")
    //@RequiresPermissions("product:attrattrgrouprelation:update")
    public R update(@RequestBody AttrGroup relation) {
        attrGroupService.updateById(relation);
        return R.ok();
    }


    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        attrGroupService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }
}
