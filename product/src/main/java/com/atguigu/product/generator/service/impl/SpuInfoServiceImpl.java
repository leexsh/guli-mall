package com.atguigu.product.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.product.feign.CouponFeignService;
import com.atguigu.product.feign.SearchFeignService;
import com.atguigu.product.feign.WareFeignService;
import com.atguigu.product.generator.domain.*;
import com.atguigu.product.generator.mapper.SpuInfoMapper;
import com.atguigu.product.generator.service.*;
import com.atguigu.product.vo.*;
import com.atguigu.to.SkuEsModel;
import com.atguigu.to.SkuHasStock;
import com.atguigu.to.SkuReductionTo;
import com.atguigu.to.SpuBound;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.TypeReference;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_spu_info(spu信息)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:03
*/
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
    implements SpuInfoService{

    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    SpuImagesService spuImagesService;

    @Autowired
    AttrService attrService;

    @Autowired
    ProductAttrValueService attrValueService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(params),
                new QueryWrapper<SpuInfo>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        SpuInfo spuInfo = new SpuInfo();
        BeanUtils.copyProperties(vo, spuInfo);
        spuInfo.setCreateTime(new Date());
        spuInfo.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfo);

        List<String> desc = vo.getDecript();
        SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
        spuInfoDesc.setSpuId(spuInfo.getId());
        spuInfoDesc.setDecript(String.join(",", desc));
        spuInfoDescService.saveSpuInfoDesc(spuInfoDesc);

        List<String> images = vo.getImages();
        spuImagesService.saveImages(spuInfo.getId(), images);

        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValue> colleat = baseAttrs.stream().map(attr->{
            ProductAttrValue productAttrValue = new ProductAttrValue();
            productAttrValue.setAttrId(attr.getAttrId());
            productAttrValue.setAttrValue(attr.getAttrValues());
            productAttrValue.setSpuId(spuInfo.getId());
            return productAttrValue;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(colleat);

        Bounds bounds = vo.getBounds();
        SpuBound spuBound = new SpuBound();
        BeanUtils.copyProperties(bounds, spuBound);
        spuBound.setSpuId(spuInfo.getId());
        R r = couponFeignService.saveSpuBounds(spuBound);
        if (r.getCode() != 0) {
            System.out.println("远程保存spu积分失败");
        }

        List<Skus> skus = vo.getSkus();
        if (skus != null && !skus.isEmpty()) {
            skus.forEach(sku -> {
                String defaultImg = "";
                for (Images image : sku.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                        break;
                    }
                }
                SkuInfo skuInfo = new SkuInfo();
                BeanUtils.copyProperties(sku, skuInfo);
                skuInfo.setSpuId(spuInfo.getId());
                skuInfo.setBrandId(spuInfo.getBrandId());
                skuInfo.setCatalogId(spuInfo.getCatalogId());
                skuInfo.setSaleCount(0L);
                skuInfo.setSkuDefaultImg(defaultImg);
                skuInfoService.saveSkuInfo(skuInfo);

                Long skuId = skuInfo.getSkuId();
                List<SkuImages> skuImages = sku.getImages().stream().map(img -> {
                    SkuImages skuImage = new SkuImages();
                    skuImage.setSkuId(skuId);
                    skuImage.setDefaultImg(img.getDefaultImg());
                    skuImage.setImgUrl(img.getImgUrl());
                    return skuImage;
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(skuImages);

                List<AttrVoEntitiy> attr = sku.getAttr();
                List<SkuSaleAttrValue> skuSaleAttrValues = attr.stream().map(attrVoEntitiy -> {
                    SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
                    skuSaleAttrValue.setSkuId(skuId);
                    skuSaleAttrValue.setAttrId(attrVoEntitiy.getAttrId());
                    skuSaleAttrValue.setAttrValue(attrVoEntitiy.getAttrValue());
                    return skuSaleAttrValue;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValues);

                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(sku, skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                couponFeignService.saveSkuReduction(skuReductionTo);
            });
        }
    }

    @Override
    public void saveBaseSpuInfo(SpuInfo infoEntity) {
        this.baseMapper.insert(infoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((w) -> {
                w.eq("spu_id", key).or().like("spu_name", key);
            });
        }

        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("publish_status", status);
        }
        String brand = (String) params.get("brand");
        if (!StringUtils.isEmpty(brand)) {
            queryWrapper.eq("brand_id", brand);
        }
        String catelog = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelog)) {
            queryWrapper.eq("catalog_id", catelog);
        }
        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        List<SkuInfo> skuInfos = skuInfoService.getSkusBySpuId(spuId);
        List<ProductAttrValue> basicAttrs = productAttrValueService.baseAttrListforspu(spuId);
        List<Long> attrIds = basicAttrs.stream().map(ProductAttrValue::getAttrId).collect(Collectors.toList());
        List<Long> searchAttrIds = basicAttrs.stream().map(ProductAttrValue::getAttrId).collect(Collectors.toList());
        Set<Long> idSet = new HashSet<>(attrIds);

        List<SkuEsModel.Attrs> attrs = basicAttrs.stream().map(item -> {
            SkuEsModel.Attrs attrss = new SkuEsModel.Attrs();
            attrss.setAttrId(item.getAttrId());
            attrss.setAttrName(item.getAttrName());
            attrss.setAttrValue(item.getAttrValue());
            return attrss;
        }).collect(Collectors.toList());

        List<Long> skuIds = skuInfos.stream().map(SkuInfo::getSkuId).collect(Collectors.toList());
        Map<Long, Boolean> map = new HashMap<>();
        try{
            R skuHasStock = wareFeignService.getSkuHasStock(skuIds);
            TypeReference<List<SkuHasStock>> typeReference = new TypeReference<List<SkuHasStock>>() {};
            List<SkuHasStock> skuHasStockList = skuHasStock.getData(typeReference);
            map = skuHasStockList.stream().collect(Collectors.toMap(SkuHasStock::getSkuId, SkuHasStock::getHasStock));
        } catch (Exception e) {
            log.error("库存服务调用失败", e);
        }

        Map<Long, Boolean> finalMap = map;
        List<SkuEsModel> skuEsModels = skuInfos.stream().map(skuInfo -> {
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(skuInfo, skuEsModel);
            skuEsModel.setAttrs(attrs);
            skuEsModel.setHasStock(finalMap.getOrDefault(skuInfo.getSkuId(), false));
            return skuEsModel;
        }).collect(Collectors.toList());
        searchFeignService.productStatusUp(skuEsModels);
    }
}




