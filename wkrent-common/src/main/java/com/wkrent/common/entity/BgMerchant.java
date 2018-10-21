/*
*
* BgMerchant.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户表
 * @author skybed
 *
 */
public class BgMerchant implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3194611597175795903L;

	// 后台商家id,唯一标识
    private String bgMerchantId;

    // 后台商家编号
    private String bgMerchantNumber;

    // 后台商家英文简称
    private String bgMerchantCode;

    // 后台商家名
    private String bgMerchantName;

    // 商家地址
    private String bgMerchantAddress;

    // 商家联系方式
    private String bgMerchantPhone;

    // 是否删除 0-否 1-是
    private String isDelete;

    // 是否启用 0-否 1-是
    private String isActive;

    // 商家描述
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
     * @return bg_merchant_id 后台商家id,唯一标识
     */
    public String getBgMerchantId() {
        return bgMerchantId;
    }

    /**
     * 
     * @param bgMerchantId 后台商家id,唯一标识
     */
    public void setBgMerchantId(String bgMerchantId) {
        this.bgMerchantId = bgMerchantId == null ? null : bgMerchantId.trim();
    }

    /**
     * 
     * @return bg_merchant_number 后台商家编号
     */
    public String getBgMerchantNumber() {
        return bgMerchantNumber;
    }

    /**
     * 
     * @param bgMerchantNumber 后台商家编号
     */
    public void setBgMerchantNumber(String bgMerchantNumber) {
        this.bgMerchantNumber = bgMerchantNumber == null ? null : bgMerchantNumber.trim();
    }

    /**
     * 
     * @return bg_merchant_code 后台商家英文简称
     */
    public String getBgMerchantCode() {
        return bgMerchantCode;
    }

    /**
     * 
     * @param bgMerchantCode 后台商家英文简称
     */
    public void setBgMerchantCode(String bgMerchantCode) {
        this.bgMerchantCode = bgMerchantCode == null ? null : bgMerchantCode.trim();
    }

    /**
     * 
     * @return bg_merchant_name 后台商家名
     */
    public String getBgMerchantName() {
        return bgMerchantName;
    }

    /**
     * 
     * @param bgMerchantName 后台商家名
     */
    public void setBgMerchantName(String bgMerchantName) {
        this.bgMerchantName = bgMerchantName == null ? null : bgMerchantName.trim();
    }

    /**
     * 
     * @return bg_merchant_address 商家地址
     */
    public String getBgMerchantAddress() {
        return bgMerchantAddress;
    }

    /**
     * 
     * @param bgMerchantAddress 商家地址
     */
    public void setBgMerchantAddress(String bgMerchantAddress) {
        this.bgMerchantAddress = bgMerchantAddress == null ? null : bgMerchantAddress.trim();
    }

    /**
     * 
     * @return bg_merchant_phone 商家联系方式
     */
    public String getBgMerchantPhone() {
        return bgMerchantPhone;
    }

    /**
     * 
     * @param bgMerchantPhone 商家联系方式
     */
    public void setBgMerchantPhone(String bgMerchantPhone) {
        this.bgMerchantPhone = bgMerchantPhone == null ? null : bgMerchantPhone.trim();
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
     * @return description 商家描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 商家描述
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