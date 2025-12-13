//package com.atguigu.utils;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//
//import java.util.Map;
//import java.util.Objects;
//
//public class query<T> {
//    public IPage<T> getPage(Map<String, Object> params) {
//        return this.getPage(params, null, false);
//    }
//
//    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean asc) {
//        long curPage = 0;
//        long limit = 10;
//        if (params.get(Constant.PAGE) != null) {
//            curPage = Long.parseLong(params.get(Constant.PAGE).toString());
//        }
//        if (params.get(Constant.LIMIT) != null) {
//            limit = Long.parseLong(params.get(Constant.LIMIT).toString());
//        }
//        Page<T> page = new Page<>(curPage, limit);
//    }
//}
