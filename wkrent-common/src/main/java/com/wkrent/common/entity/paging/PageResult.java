package com.wkrent.common.entity.paging;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 分页查询结果
 * @author Administrator
 */
public class PageResult<T>{

    /**
     * 返回数据
     */
    private List<T> rows;

    /**
     * 总条数
     */
    private int total;

    public PageResult(){
        this.rows = Lists.newArrayList();
        this.total = 0;
    }

    public List<T> getRows()
    {
        return this.rows;
    }

    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }

    public int getTotal()
    {
        return this.total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }
}

