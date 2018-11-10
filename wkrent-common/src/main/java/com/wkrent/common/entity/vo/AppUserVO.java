package com.wkrent.common.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * app用户信息
 * @author skybed
 *
 */
public class AppUserVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4077932692201692366L;

    /**
     *app用户id,唯一标识
     */
    private String appUserId;

    /**
     * 用户编号
     */
    private String appUserNumber;

    /**
     * 用户名
     */
    private String appUserName;

    /**
     * 手机号
     */
    private String appUserPhone;

    /**
     * 电子邮箱
     */
    private String appUserEmail;

    /**
     * 性别 0-男 1-女
     */
    private String appUserSex;

    /**
     * 生日
     */
    private String appUserBirthday;

    /**
     * 年龄
     */
    private Byte appUserAge;

    /**
     * 所属国籍
     */
    private String appUserCountry;

    /**
     * 所属城市
     */
    private String appUserCity;

    /**
     * 微信OPENID
     */
    private String appUserWechatOpenid;

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

    /**
     * 
     * @return app_user_id app用户id,唯一标识
     */
    public String getAppUserId() {
        return appUserId;
    }

    /**
     * 
     * @param appUserId app用户id,唯一标识
     */
    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId == null ? null : appUserId.trim();
    }

    /**
     * 
     * @return app_user_number 用户编号
     */
    public String getAppUserNumber() {
        return appUserNumber;
    }

    /**
     * 
     * @param appUserNumber 用户编号
     */
    public void setAppUserNumber(String appUserNumber) {
        this.appUserNumber = appUserNumber == null ? null : appUserNumber.trim();
    }

    /**
     * 
     * @return app_user_name 用户名
     */
    public String getAppUserName() {
        return appUserName;
    }

    /**
     * 
     * @param appUserName 用户名
     */
    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName == null ? null : appUserName.trim();
    }

    /**
     * 
     * @return app_user_phone 手机号
     */
    public String getAppUserPhone() {
        return appUserPhone;
    }

    /**
     * 
     * @param appUserPhone 手机号
     */
    public void setAppUserPhone(String appUserPhone) {
        this.appUserPhone = appUserPhone == null ? null : appUserPhone.trim();
    }

    /**
     * 
     * @return app_user_email 电子邮箱
     */
    public String getAppUserEmail() {
        return appUserEmail;
    }

    /**
     * 
     * @param appUserEmail 电子邮箱
     */
    public void setAppUserEmail(String appUserEmail) {
        this.appUserEmail = appUserEmail == null ? null : appUserEmail.trim();
    }

    /**
     * 
     * @return app_user_sex 性别 0-男 1-女
     */
    public String getAppUserSex() {
        return appUserSex;
    }

    /**
     * 
     * @param appUserSex 性别 0-男 1-女
     */
    public void setAppUserSex(String appUserSex) {
        this.appUserSex = appUserSex == null ? null : appUserSex.trim();
    }

    /**
     * 
     * @return app_user_birthday 生日
     */
    public String getAppUserBirthday() {
        return appUserBirthday;
    }

    /**
     * 
     * @param appUserBirthday 生日
     */
    public void setAppUserBirthday(String appUserBirthday) {
        this.appUserBirthday = appUserBirthday == null ? null : appUserBirthday.trim();
    }

    /**
     * 
     * @return app_user_age 年龄
     */
    public Byte getAppUserAge() {
        return appUserAge;
    }

    /**
     * 
     * @param appUserAge 年龄
     */
    public void setAppUserAge(Byte appUserAge) {
        this.appUserAge = appUserAge;
    }

    /**
     * 
     * @return app_user_country 所属国籍
     */
    public String getAppUserCountry() {
        return appUserCountry;
    }

    /**
     * 
     * @param appUserCountry 所属国籍
     */
    public void setAppUserCountry(String appUserCountry) {
        this.appUserCountry = appUserCountry == null ? null : appUserCountry.trim();
    }

    /**
     * 
     * @return app_user_city 所属城市
     */
    public String getAppUserCity() {
        return appUserCity;
    }

    /**
     * 
     * @param appUserCity 所属城市
     */
    public void setAppUserCity(String appUserCity) {
        this.appUserCity = appUserCity == null ? null : appUserCity.trim();
    }

    /**
     * 
     * @return app_user_wechat_openId 微信OPENID
     */
    public String getAppUserWechatOpenid() {
        return appUserWechatOpenid;
    }

    /**
     * 
     * @param appUserWechatOpenid 微信OPENID
     */
    public void setAppUserWechatOpenid(String appUserWechatOpenid) {
        this.appUserWechatOpenid = appUserWechatOpenid == null ? null : appUserWechatOpenid.trim();
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