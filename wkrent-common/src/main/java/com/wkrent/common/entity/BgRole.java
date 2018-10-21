/*
*
* BgRole.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 * @author skybed
 *
 */
public class BgRole implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7818968737237159554L;

	// 角色id,唯一标识
    private String bgRoleId;

    // 角色名
    private String bgRoleName;

    // 是否删除 0-否 1-是
    private String isDelete;

    // 是否启用 0-否 1-是
    private String isActive;

    // 角色描述
    private String descrption;

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
     * @return bg_role_id 角色id,唯一标识
     */
    public String getBgRoleId() {
        return bgRoleId;
    }

    /**
     * 
     * @param bgRoleId 角色id,唯一标识
     */
    public void setBgRoleId(String bgRoleId) {
        this.bgRoleId = bgRoleId == null ? null : bgRoleId.trim();
    }

    /**
     * 
     * @return bg_role_name 角色名
     */
    public String getBgRoleName() {
        return bgRoleName;
    }

    /**
     * 
     * @param bgRoleName 角色名
     */
    public void setBgRoleName(String bgRoleName) {
        this.bgRoleName = bgRoleName == null ? null : bgRoleName.trim();
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
     * @return descrption 角色描述
     */
    public String getDescrption() {
        return descrption;
    }

    /**
     * 
     * @param descrption 角色描述
     */
    public void setDescrption(String descrption) {
        this.descrption = descrption == null ? null : descrption.trim();
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