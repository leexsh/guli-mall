package com.atguigu.mallsearch.service;

import com.atguigu.mallsearch.vo.SearchParam;

import javax.naming.directory.SearchResult;

public interface MallSearchService {
    SearchResult search(SearchParam param);
}
