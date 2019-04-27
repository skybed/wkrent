/*
*
* BgDataDict.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典枚举类型表
 * @author skybed
 *
 */
public class BgQuestionCategory implements Serializable {
	
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
    private int bgQuestionCatIndex;

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
     * 备注 预留字段
     */
    private String remark;


    public String getBgQuestionCatId() {
        return bgQuestionCatId;
    }

    public void setBgQuestionCatId(String bgQuestionCatId) {
        this.bgQuestionCatId = bgQuestionCatId == null ? null : bgQuestionCatId.trim();
    }

    public String getBgQuestionCatName() {
        return bgQuestionCatName;
    }

    public void setBgQuestionCatName(String bgQuestionCatName) {
        this.bgQuestionCatName = bgQuestionCatName == null ? null : bgQuestionCatName.trim();
    }

    public int getBgQuestionCatIndex() {
        return bgQuestionCatIndex;
    }

    public void setBgQuestionCatIndex(int bgQuestionCatIndex) {
        this.bgQuestionCatIndex = bgQuestionCatIndex;
    }

    /**
     * 
     * @return is_delete 是否删除 0-否 1-是
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 
     * @param isDelete 是否删除 0-否 1-是
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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