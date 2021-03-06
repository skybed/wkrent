package com.wkrent.common.entity.vo;

import java.util.Date;

/**
 * 模块设置
 * @author Administrator
 */
public class BgModuleVO {

    /**
     * 模块设置id
     */
    private String bgModuleId;

    /**
     * 模块设置类型（1：服务协议；2：租房说明）
     */
    private String bgModuleType;

    /**
     * 模块设置内容（富文本框）
     */
    private String bgModuleValue;


    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;

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
     *  更新时间
     */
    private Date updateTime;

    /**
     * 客服微信文件id
     */
    private String customerServiceFileId;

    /**
     * 平台微信附件id
     */
    private String platformFileId;

    public String getBgModuleId() {
        return bgModuleId;
    }

    public void setBgModuleId(String bgModuleId) {
        this.bgModuleId = bgModuleId;
    }

    public String getBgModuleType() {
        return bgModuleType;
    }

    public void setBgModuleType(String bgModuleType) {
        this.bgModuleType = bgModuleType;
    }

    public String getBgModuleValue() {
        return bgModuleValue;
    }

    public void setBgModuleValue(String bgModuleValue) {
        this.bgModuleValue = bgModuleValue;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerServiceFileId() {
        return customerServiceFileId;
    }

    public void setCustomerServiceFileId(String customerServiceFileId) {
        this.customerServiceFileId = customerServiceFileId;
    }

    public String getPlatformFileId() {
        return platformFileId;
    }

    public void setPlatformFileId(String platformFileId) {
        this.platformFileId = platformFileId;
    }
}
