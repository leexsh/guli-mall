package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.MemberCollectSpu;
import com.atguigu.member.generator.mapper.MemberCollectSpuMapper;
import com.atguigu.member.generator.service.MemberCollectSpuService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_collect_spu(会员收藏的商品)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("memberCollectSpuService")
public class MemberCollectSpuServiceImpl extends ServiceImpl<MemberCollectSpuMapper, MemberCollectSpu>
    implements MemberCollectSpuService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberCollectSpu> page = this.page(
                new Query<MemberCollectSpu>().getPage(params),
                new QueryWrapper<MemberCollectSpu>()
        );

        return new PageUtils(page);
    }
}




