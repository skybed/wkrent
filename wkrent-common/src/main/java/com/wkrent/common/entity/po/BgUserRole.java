/*
*
* BgUserRole.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.po;

import java.io.Serializable;

/**
 * 用户角色表
 * @author skybed
 *
 */
public class BgUserRole implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5820871335767716083L;

    /**
     * 后台用户角色id,唯一标识
     */
    private String bgUserRoleId;

    /**
     * 后台用户id
     */
    private String bgUserId;

    /**
     * 后台角色id
     */
    private String bgRoleId;

    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;

    /**
     * 备注 预留字段
     */
    private String remark;

    /**
     * 
     * @return bg_user_role_id 后台用户角色id,唯一标识
     */
    public String getBgUserRoleId() {
        return bgUserRoleId;
    }

    /**
     * 
     * @param bgUserRoleId 后台用户角色id,唯一标识
     */
    public void setBgUserRoleId(String bgUserRoleId) {
        this.bgUserRoleId = bgUserRoleId == null ? null : bgUserRoleId.trim();
    }

    /**
     * 
     * @return bg_user_id 后台用户id
     */
    public String getBgUserId() {
        return bgUserId;
    }

    /**
     * 
     * @param bgUserId 后台用户id
     */
    public void setBgUserId(String bgUserId) {
        this.bgUserId = bgUserId == null ? null : bgUserId.trim();
    }

    /**
     * 
     * @return bg_role_id 后台角色id
     */
    public String getBgRoleId() {
        return bgRoleId;
    }

    /**
     * 
     * @param bgRoleId 后台角色id
     */
    public void setBgRoleId(String bgRoleId) {
        this.bgRoleId = bgRoleId == null ? null : bgRoleId.trim();
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