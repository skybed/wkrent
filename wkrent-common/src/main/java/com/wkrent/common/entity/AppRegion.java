/*
*
* AppRegion.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-24
*/
package com.wkrent.common.entity;

import java.io.Serializable;
import java.util.Date;

public class AppRegion implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 941376872281237989L;

	// 地区id,主键
    private String regionId;

    // 地区名
    private String regionName;

    // 地区中文名
    private String regionCnName;

    // 地区码
    private String regionCode;

    // 代号
    private String regionMark;

    // 是否删除 0-否 1-是
    private String isDelete;

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
     * @return region_id 地区id,主键
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * 
     * @param regionId 地区id,主键
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    /**
     * 
     * @return region_name 地区名
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * 
     * @param regionName 地区名
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    /**
     * 
     * @return region_cn_name 地区中文名
     */
    public String getRegionCnName() {
        return regionCnName;
    }

    /**
     * 
     * @param regionCnName 地区中文名
     */
    public void setRegionCnName(String regionCnName) {
        this.regionCnName = regionCnName == null ? null : regionCnName.trim();
    }

    /**
     * 
     * @return region_code 地区码
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * 
     * @param regionCode 地区码
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    /**
     * 
     * @return region_mark 代号
     */
    public String getRegionMark() {
        return regionMark;
    }

    /**
     * 
     * @param regionMark 代号
     */
    public void setRegionMark(String regionMark) {
        this.regionMark = regionMark == null ? null : regionMark.trim();
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