package com.wkrent.common.entity.paging;

/**
 * 排序信息
 * @author Administrator
 */
public class Sort
{
    /**
     * 排序类型
     */
    private String type;

    /**
     * 排序字段
     */
    private String name;

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
