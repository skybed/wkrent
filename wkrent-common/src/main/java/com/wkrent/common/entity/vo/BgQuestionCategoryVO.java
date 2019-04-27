/*
*
* BgDataDict.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 数据字典枚举类型表
 * @author skybed
 *
 */
public class BgQuestionCategoryVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5369227689370844939L;

    /**
     * 问题分类Id
     */
    private String bgQuestionCatId;

    /**
     * 问题分类名称
     */
    private String bgQuestionCatName;

    /**
     * 问题分类排序
     */
    private Integer bgQuestionCatIndex;

    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 分页信息
     */
    private Page page;

    /**
     * 问题分类IdList
     */
    private List<String> bgQuestionCatIdList;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getBgQuestionCatId() {
        return bgQuestionCatId;
    }

    public void setBgQuestionCatId(String bgQuestionCatId) {
        this.bgQuestionCatId = bgQuestionCatId;
    }

    public String getBgQuestionCatName() {
        return bgQuestionCatName;
    }

    public void setBgQuestionCatName(String bgQuestionCatName) {
        this.bgQuestionCatName = bgQuestionCatName;
    }

    public Integer getBgQuestionCatIndex() {
        return bgQuestionCatIndex;
    }

    public void setBgQuestionCatIndex(Integer bgQuestionCatIndex) {
        this.bgQuestionCatIndex = bgQuestionCatIndex;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getBgQuestionCatIdList() {
        return bgQuestionCatIdList;
    }

    public void setBgQuestionCatIdList(List<String> bgQuestionCatIdList) {
        this.bgQuestionCatIdList = bgQuestionCatIdList;
    }
}