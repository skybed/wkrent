/*
*
* BgDataDictValue.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 常见问题
 * @author skybed
 *
 */
public class BgQuestion implements Serializable {
	
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

    /**
     * 
     * @return is_detele 是否删除 0-否1-是
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 
     * @param isDelete 是否删除 0-否1-是
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * 
     * @return create_by 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_by 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * 
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return remark 备注 预留字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 备注 预留字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}