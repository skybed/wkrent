/*
*
* BgUser.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户表
 * @author skybed
 *
 */
public class BgUser implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3518724783047793480L;

    /**
     * 后台用户id,唯一标识
     */
    private String bgUserId;

    /**
     * 后台用户账号
     */
    private String bgUserAccount;

    /**
     * 后台用户登录密码
     */
    private String bgUserPwd;

    /**
     * 后台用户名
     */
    private String bgUserName;

    /**
     * 后台用户性别 0-男 1-女
     */
    private String bgUserSex;

    /**
     * 后台用户联系方式
      */
    private String bgUserPhone;

    /**
     * 后台用户联系邮箱
     */
    private String bgUserEmail;

    /**
     * 后台用户工号
     */
    private String bgUserEmpId;

    /**
     * 后台用户部门
     */
    private String bgUserDept;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;

    /**
     * 是否启用 0-否 1-是
     */
    private String isActive;

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
     * @return bg_user_id 后台用户id,唯一标识
     */
    public String getBgUserId() {
        return bgUserId;
    }

    /**
     * 
     * @param bgUserId 后台用户id,唯一标识
     */
    public void setBgUserId(String bgUserId) {
        this.bgUserId = bgUserId == null ? null : bgUserId.trim();
    }

    /**
     * 
     * @return bg_user_account 后台用户账号
     */
    public String getBgUserAccount() {
        return bgUserAccount;
    }

    /**
     * 
     * @param bgUserAccount 后台用户账号
     */
    public void setBgUserAccount(String bgUserAccount) {
        this.bgUserAccount = bgUserAccount == null ? null : bgUserAccount.trim();
    }

    /**
     * 
     * @return bg_user_pwd 后台用户登录密码
     */
    public String getBgUserPwd() {
        return bgUserPwd;
    }

    /**
     * 
     * @param bgUserPwd 后台用户登录密码
     */
    public void setBgUserPwd(String bgUserPwd) {
        this.bgUserPwd = bgUserPwd == null ? null : bgUserPwd.trim();
    }

    /**
     * 
     * @return bg_user_name 后台用户名
     */
    public String getBgUserName() {
        return bgUserName;
    }

    /**
     * 
     * @param bgUserName 后台用户名
     */
    public void setBgUserName(String bgUserName) {
        this.bgUserName = bgUserName == null ? null : bgUserName.trim();
    }

    /**
     * 
     * @return bg_user_sex 后台用户性别 0-男 1-女
     */
    public String getBgUserSex() {
        return bgUserSex;
    }

    /**
     * 
     * @param bgUserSex 后台用户性别 0-男 1-女
     */
    public void setBgUserSex(String bgUserSex) {
        this.bgUserSex = bgUserSex == null ? null : bgUserSex.trim();
    }

    /**
     * 
     * @return bg_user_phone 后台用户联系方式
     */
    public String getBgUserPhone() {
        return bgUserPhone;
    }

    /**
     * 
     * @param bgUserPhone 后台用户联系方式
     */
    public void setBgUserPhone(String bgUserPhone) {
        this.bgUserPhone = bgUserPhone == null ? null : bgUserPhone.trim();
    }

    /**
     * 
     * @return bg_user_email 后台用户联系邮箱
     */
    public String getBgUserEmail() {
        return bgUserEmail;
    }

    /**
     * 
     * @param bgUserEmail 后台用户联系邮箱
     */
    public void setBgUserEmail(String bgUserEmail) {
        this.bgUserEmail = bgUserEmail == null ? null : bgUserEmail.trim();
    }

    /**
     * 
     * @return bg_user_emp_id 后台用户工号
     */
    public String getBgUserEmpId() {
        return bgUserEmpId;
    }

    /**
     * 
     * @param bgUserEmpId 后台用户工号
     */
    public void setBgUserEmpId(String bgUserEmpId) {
        this.bgUserEmpId = bgUserEmpId == null ? null : bgUserEmpId.trim();
    }

    /**
     * 
     * @return bg_user_dept 后台用户部门
     */
    public String getBgUserDept() {
        return bgUserDept;
    }

    /**
     * 
     * @param bgUserDept 后台用户部门
     */
    public void setBgUserDept(String bgUserDept) {
        this.bgUserDept = bgUserDept == null ? null : bgUserDept.trim();
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