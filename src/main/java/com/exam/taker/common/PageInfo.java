package com.exam.taker.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageInfo <T> implements Serializable {
    private long page;
    private long pageSize;
    private long pages;
    private long total;
    private List<T> list;


    public  PageInfo(){

    }
    public PageInfo(Page<T> pageInfo){
        this.page=pageInfo.getCurrent();
        this.pageSize=pageInfo.getSize();
        this.pages=pageInfo.getPages();
        this.total=pageInfo.getTotal();
        this.list=pageInfo.getRecords();
    }

    public PageInfo(IPage<T> pageInfo){
        this.page=pageInfo.getCurrent();
        this.pageSize=pageInfo.getSize();
        this.pages=pageInfo.getPages();
        this.total=pageInfo.getTotal();
        this.list=pageInfo.getRecords();
    }
}
