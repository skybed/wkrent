/*
*
* AppAttention.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收藏表
 * @author skybed
 *
 */
public class AppAttention implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2885915647080271768L;

	// 收藏id,唯一标识
    private String appAttentionId;

    // 所属用户
    private String appUserId;

    // 收藏房源信息id
    private String appRoomId;

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
     * @return app_attention_id 收藏id,唯一标识
     */
    public String getAppAttentionId() {
        return appAttentionId;
    }

    /**
     * 
     * @param appAttentionId 收藏id,唯一标识
     */
    public void setAppAttentionId(String appAttentionId) {
        this.appAttentionId = appAttentionId == null ? null : appAttentionId.trim();
    }

    /**
     * 
     * @return app_user_id 所属用户
     */
    public String getAppUserId() {
        return appUserId;
    }

    /**
     * 
     * @param appUserId 所属用户
     */
    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId == null ? null : appUserId.trim();
    }

    /**
     * 
     * @return app_room_id 收藏房源信息id
     */
    public String getAppRoomId() {
        return appRoomId;
    }

    /**
     * 
     * @param appRoomId 收藏房源信息id
     */
    public void setAppRoomId(String appRoomId) {
        this.appRoomId = appRoomId == null ? null : appRoomId.trim();
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