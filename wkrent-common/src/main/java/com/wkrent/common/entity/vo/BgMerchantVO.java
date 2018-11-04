/*
*
* BgMerchant.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商户表
 * @author skybed
 *
 */
public class BgMerchantVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3194611597175795903L;

    /**
     * 后台商家id,唯一标识
     */
    private String bgMerchantId;

    /**
     * 后台商家编号
     */
    private String bgMerchantNumber;

    /**
     * 后台商家名
     */
    private String bgMerchantName;

    /**
     * 是否启用 0-否 1-是
     */
    private String isActive;

    /**
     * 商家描述
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
     * 营业执照idList
     */
    private List<String> fileIdList;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 分页信息
     */
    private Page page;

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

    public List<String> getFileIdList() {
        return fileIdList;
    }

    public void setFileIdList(List<String> fileIdList) {
        this.fileIdList = fileIdList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}