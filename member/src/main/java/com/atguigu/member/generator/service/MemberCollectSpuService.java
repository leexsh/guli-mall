package com.atguigu.member.generator.service;

import com.atguigu.member.generator.domain.MemberCollectSpu;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_collect_spu(会员收藏的商品)】的数据库操作Service
* @createDate 2025-12-13 21:48:49
*/
public interface MemberCollectSpuService extends IService<MemberCollectSpu> {
    PageUtils queryPage(Map<String, Object> params);
}
