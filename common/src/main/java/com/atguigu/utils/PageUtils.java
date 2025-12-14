package com.atguigu.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    public PageUtils(List<?> list, int totalCount, int currentPage, int pageSize) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public PageUtils(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = (int)page.getSize();
        this.currentPage = (int)page.getCurrent();
        this.totalPage = (int)page.getPages();
    }

    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    private int pageSize;
    private int totalPage;
    private int currentPage;
    private List<?> list;
}
