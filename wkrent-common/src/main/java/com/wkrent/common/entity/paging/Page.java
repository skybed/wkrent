package com.wkrent.common.entity.paging;

import java.util.List;

/**
 * 分页对象
 */
public class Page
{
    /**
     * 默认页容量
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 最大页容量
     */
    public static final int MAX_PAGE_SIZE = 500;

    /**
     * 页容量
     */
    private int pageSize;

    private int recordsTotal;

    /**
     *总页数
     */
    private int pageTotal;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 开始行
     */
    private int startRow;

    /**
     * 结束行
     */
    private int endRow;

    /**
     * 排序信息
     */
    private List<Sort> sortList;

    public Page()
    {
        this.currentPage = 1;
        this.pageSize = 20;
    }

    public Page(int currentPage, int size)
    {
        this.currentPage = currentPage;
        this.pageSize = size;
    }

    public int getPageSize()
    {
        return this.pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getRecordsTotal()
    {
        return this.recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal)
    {
        this.recordsTotal = recordsTotal;
    }

    public int getPageTotal()
    {
        return this.pageTotal;
    }

    public void setPageTotal(int pageTotal)
    {
        this.pageTotal = pageTotal;
    }

    public int getCurrentPage()
    {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public List<Sort> getSortList()
    {
        return this.sortList;
    }

    public void setSortList(List<Sort> sortList)
    {
        this.sortList = sortList;
    }

    public int getStartRow() {
        if(this.pageSize != 0 && this.currentPage >= 1){
            return pageSize * (currentPage - 1);
        }
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        if(this.pageSize != 0 && this.currentPage >= 1){
            return pageSize * currentPage;
        }
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }
}

