/*
*
* BgRoleAuth.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity;

import java.io.Serializable;

/**
 * 角色权限表
 * @author skybed
 *
 */
public class BgRoleAuth implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1560841935853195724L;

	// 角色权限Id,唯一标识
    private String bgRoleAuthId;

    // 角色id
    private String bgRoleId;

    // 菜单id
    private String bgMenuId;

    // 是否删除 0-是 1-否
    private String isDelete;

    // 备注 预留字段
    private String remark;

    /**
     * 
     * @return bg_role_auth_id 角色权限Id,唯一标识
     */
    public String getBgRoleAuthId() {
        return bgRoleAuthId;
    }

    /**
     * 
     * @param bgRoleAuthId 角色权限Id,唯一标识
     */
    public void setBgRoleAuthId(String bgRoleAuthId) {
        this.bgRoleAuthId = bgRoleAuthId == null ? null : bgRoleAuthId.trim();
    }

    /**
     * 
     * @return bg_role_id 角色id
     */
    public String getBgRoleId() {
        return bgRoleId;
    }

    /**
     * 
     * @param bgRoleId 角色id
     */
    public void setBgRoleId(String bgRoleId) {
        this.bgRoleId = bgRoleId == null ? null : bgRoleId.trim();
    }

    /**
     * 
     * @return bg_menu_id 菜单id
     */
    public String getBgMenuId() {
        return bgMenuId;
    }

    /**
     * 
     * @param bgMenuId 菜单id
     */
    public void setBgMenuId(String bgMenuId) {
        this.bgMenuId = bgMenuId == null ? null : bgMenuId.trim();
    }

    /**
     * 
     * @return is_delete 是否删除 0-是 1-否
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 
     * @param isDelete 是否删除 0-是 1-否
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