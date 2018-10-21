/*
*
* BgDataDictValue.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典枚举值表
 * @author skybed
 *
 */
public class BgDataDictValue implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5801113974059232853L;

	// 数据字典值id,唯一标识
    private String bgDataDictValueId;

    // 数据字典type
    private String bgDataDictId;

    // 数据字典值
    private String bgDataDictValue;

    // 是否删除 0-否1-是
    private String isDetele;

    // 是否启用 0-否 1-是
    private String isActive;

    // 描述
    private String description;

    // 创建者
    private String createBy;

    // 创建时间
    private Date createTime;

    // 更新者
    private String updateBy;

    // 更新时间
    private Date updateTime;

    // 备注 预留字段
    private String remark;

    /**
     * 
     * @return bg_data_dict_value_id 数据字典值id,唯一标识
     */
    public String getBgDataDictValueId() {
        return bgDataDictValueId;
    }

    /**
     * 
     * @param bgDataDictValueId 数据字典值id,唯一标识
     */
    public void setBgDataDictValueId(String bgDataDictValueId) {
        this.bgDataDictValueId = bgDataDictValueId == null ? null : bgDataDictValueId.trim();
    }

    /**
     * 
     * @return bg_data_dict_id 数据字典type
     */
    public String getBgDataDictId() {
        return bgDataDictId;
    }

    /**
     * 
     * @param bgDataDictId 数据字典type
     */
    public void setBgDataDictId(String bgDataDictId) {
        this.bgDataDictId = bgDataDictId == null ? null : bgDataDictId.trim();
    }

    /**
     * 
     * @return bg_data_dict_value 数据字典值
     */
    public String getBgDataDictValue() {
        return bgDataDictValue;
    }

    /**
     * 
     * @param bgDataDictValue 数据字典值
     */
    public void setBgDataDictValue(String bgDataDictValue) {
        this.bgDataDictValue = bgDataDictValue == null ? null : bgDataDictValue.trim();
    }

    /**
     * 
     * @return is_detele 是否删除 0-否1-是
     */
    public String getIsDetele() {
        return isDetele;
    }

    /**
     * 
     * @param isDetele 是否删除 0-否1-是
     */
    public void setIsDetele(String isDetele) {
        this.isDetele = isDetele == null ? null : isDetele.trim();
    }

    /**
     * 
     * @return is_active 是否启用 0-否 1-是
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * 
     * @param isActive 是否启用 0-否 1-是
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive == null ? null : isActive.trim();
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