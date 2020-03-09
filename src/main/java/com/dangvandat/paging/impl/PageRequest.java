package com.dangvandat.paging.impl;

import com.dangvandat.paging.Pageble;
import com.dangvandat.paging.Sorter;

public class PageRequest implements Pageble {

    private Integer page;
    private Integer maxPageItem;
    private Sorter sorter;

    public PageRequest(Integer page , Integer maxPageItem , Sorter sorter){
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.sorter = sorter;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getOffSet() {
        if(page != null && maxPageItem != null){
            return (page - 1) * maxPageItem;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return maxPageItem;
    }

    @Override
    public Sorter getSorter() {
        return sorter;
    }
}
