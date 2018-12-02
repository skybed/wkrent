/*
*
* BgUserRole.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.vo;

import java.io.Serializable;

/**
 * 用户角色表
 * @author skybed
 *
 */
public class BgUserRoleVO implements Serializable {
	
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
     * 平台用户名称
     */
    private String bgUserName;

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

    public String getBgUserName() {
        return bgUserName;
    }

    public void setBgUserName(String bgUserName) {
        this.bgUserName = bgUserName;
    }
}
