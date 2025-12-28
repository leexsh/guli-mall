package com.atguigu.mallsearch.controller;

import ch.qos.logback.core.model.Model;
import com.atguigu.mallsearch.service.MallSearchService;
import com.atguigu.mallsearch.vo.SearchParam;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.SearchResult;

@Slf4j
@RequestMapping(value = "/search/save")
@RestController
public class SearchController {
    @Autowired
    private MallSearchService mallSearchService;

    /**
     * 自动将页面提交过来的所有请求参数封装成我们指定的对象
     *
     * @param param
     * @return
     */
    @GetMapping(value = "/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {

        param.set_queryString(request.getQueryString());

        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);

//        model.addAttribute("result", result);

        return "list";
    }
}
