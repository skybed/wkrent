/*
*
* BgDataDictValue.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 常见问题
 * @author skybed
 *
 */
public class BgQuestionVO implements Serializable {
	
    /**
	 *
	 */
	private static final long serialVersionUID = -5801113974059232853L;

    /**
     * 问题id
     */
    private String bgQuestionId;

    /**
     * 问题分类id
     */
    private String bgQuestionCatId;

    /**
     * 问题分类名称
     */
    private String bgQuestionCatName;

    /**
     * 问题名称
     */
    private String bgQuestion;

    /**
     * 问题答案
     */
    private String bgQuestionAnswer;

    /**
     * 是否删除 0-否1-是
     */
    private String isDelete;

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
     * 备注 预留字段
     */
    private String remark;


    /**
     * 分页信息
     */
    private Page page;

    /**
     * 问题idList
     */
    private List<String> bgQuestionIdList;


    public String getBgQuestionId() {
        return bgQuestionId;
    }

    public void setBgQuestionId(String bgQuestionId) {
        this.bgQuestionId = bgQuestionId == null ? null : bgQuestionId.trim();
    }

    public String getBgQuestionCatId() {
        return bgQuestionCatId;
    }

    public void setBgQuestionCatId(String bgQuestionCatId) {
        this.bgQuestionCatId = bgQuestionCatId == null ? null : bgQuestionCatId.trim();
    }

    public String getBgQuestion() {
        return bgQuestion;
    }

    public void setBgQuestion(String bgQuestion) {
        this.bgQuestion = bgQuestion == null ? null : bgQuestion.trim();
    }

    public String getBgQuestionAnswer() {
        return bgQuestionAnswer;
    }

    public void setBgQuestionAnswer(String bgQuestionAnswer) {
        this.bgQuestionAnswer = bgQuestionAnswer == null ? null : bgQuestionAnswer.trim();
    }

    public String getBgQuestionCatName() {
        return bgQuestionCatName;
    }

    public void setBgQuestionCatName(String bgQuestionCatName) {
        this.bgQuestionCatName = bgQuestionCatName;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<String> getBgQuestionIdList() {
        return bgQuestionIdList;
    }

    public void setBgQuestionIdList(List<String> bgQuestionIdList) {
        this.bgQuestionIdList = bgQuestionIdList;
    }
}