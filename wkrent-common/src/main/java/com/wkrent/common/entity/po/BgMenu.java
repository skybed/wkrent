/*
*
* BgMenu.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 * @author skybed
 *
 */
public class BgMenu implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7144636941664819150L;

    /**
     * 菜单Id,唯一标识
     */
    private String bgMenuId;

    /**
     * 菜单名
     */
    private String bgMenuName;

    /**
     * 菜单图标
     */
    private String bgMenuIcon;

    /**
     * 菜单地址
     */
    private String bgMenuUrl;

    /**
     * 父节点菜单Id
     */
    private String bgMenuParentId;

    /**
     * 菜单类型 0-菜单 1-按钮
     */
    private String bgMenuType;

    /**
     * 菜单排序
     */
    private String bgMenuSort;

    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;

    /**
     * 是否启用 0-否 1-是
     */
    private String isActive;

    /**
     * 菜单描述
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

    /**
     * 
     * @return bg_menu_id 菜单Id,唯一标识
     */
    public String getBgMenuId() {
        return bgMenuId;
    }

    /**
     * 
     * @param bgMenuId 菜单Id,唯一标识
     */
    public void setBgMenuId(String bgMenuId) {
        this.bgMenuId = bgMenuId == null ? null : bgMenuId.trim();
    }

    /**
     * 
     * @return bg_menu_name 菜单名
     */
    public String getBgMenuName() {
        return bgMenuName;
    }

    /**
     * 
     * @param bgMenuName 菜单名
     */
    public void setBgMenuName(String bgMenuName) {
        this.bgMenuName = bgMenuName == null ? null : bgMenuName.trim();
    }

    /**
     * 
     * @return bg_menu_icon 菜单图标
     */
    public String getBgMenuIcon() {
        return bgMenuIcon;
    }

    /**
     * 
     * @param bgMenuIcon 菜单图标
     */
    public void setBgMenuIcon(String bgMenuIcon) {
        this.bgMenuIcon = bgMenuIcon == null ? null : bgMenuIcon.trim();
    }

    /**
     * 
     * @return bg_menu_url 菜单地址
     */
    public String getBgMenuUrl() {
        return bgMenuUrl;
    }

    /**
     * 
     * @param bgMenuUrl 菜单地址
     */
    public void setBgMenuUrl(String bgMenuUrl) {
        this.bgMenuUrl = bgMenuUrl == null ? null : bgMenuUrl.trim();
    }

    /**
     * 
     * @return bg_menu_parent_id 父节点菜单Id
     */
    public String getBgMenuParentId() {
        return bgMenuParentId;
    }

    /**
     * 
     * @param bgMenuParentId 父节点菜单Id
     */
    public void setBgMenuParentId(String bgMenuParentId) {
        this.bgMenuParentId = bgMenuParentId == null ? null : bgMenuParentId.trim();
    }

    /**
     * 
     * @return bg_menu_type 菜单类型 0-菜单 1-按钮
     */
    public String getBgMenuType() {
        return bgMenuType;
    }

    /**
     * 
     * @param bgMenuType 菜单类型 0-菜单 1-按钮
     */
    public void setBgMenuType(String bgMenuType) {
        this.bgMenuType = bgMenuType == null ? null : bgMenuType.trim();
    }

    /**
     * 
     * @return bg_menu_sort 菜单排序
     */
    public String getBgMenuSort() {
        return bgMenuSort;
    }

    /**
     * 
     * @param bgMenuSort 菜单排序
     */
    public void setBgMenuSort(String bgMenuSort) {
        this.bgMenuSort = bgMenuSort == null ? null : bgMenuSort.trim();
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
     * @return description 菜单描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 菜单描述
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