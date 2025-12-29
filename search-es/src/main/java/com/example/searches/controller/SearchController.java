package com.example.searches.controller;

import ch.qos.logback.core.model.Model;
import com.example.searches.service.MallSearchService;
import com.example.searches.vo.SearchParam;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.naming.directory.SearchResult;

@Controller
public class SearchController {
    @Autowired
    private MallSearchService searchService;
    @GetMapping(value = "/search")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {

        param.set_queryString(request.getQueryString());

        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = searchService.search(param);

//        model.addAttribute("result", result);

        return "list";
    }

}
