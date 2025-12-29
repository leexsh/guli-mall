package com.example.searches.service;

import com.example.searches.vo.SearchParam;

import javax.naming.directory.SearchResult;

public interface MallSearchService {
    SearchResult search(SearchParam param);
}
