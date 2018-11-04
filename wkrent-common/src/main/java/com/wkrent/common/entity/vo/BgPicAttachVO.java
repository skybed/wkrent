/*
*
* BgPicAttach.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 附件表
 * @author skybed
 *
 */
@ApiModel
public class BgPicAttachVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5444827541269317896L;

    /**
     * 附件id,唯一标识
     */
    private String picAttachId;

    /**
     * 附件名
     */
    private String picAttachName;

    /**
     * 附件类型
     */
    private String picAttachType;

    /**
     * 附件地址
     */
    private String picAttachUrl;

    /**
     * 附件大小
     */
    private String picAttachFileVolume;

    /**
     * 附件类型 比如jpg png等
     */
    private String picAttachFileType;

    /**
     * 附件归属id
     */
    private String picAttachOwner;

    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;

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
     * @return pic_attach_id 附件id,唯一标识
     */
    public String getPicAttachId() {
        return picAttachId;
    }

    /**
     * 
     * @param picAttachId 附件id,唯一标识
     */
    public void setPicAttachId(String picAttachId) {
        this.picAttachId = picAttachId == null ? null : picAttachId.trim();
    }

    /**
     * 
     * @return pic_attach_name 附件名
     */
    public String getPicAttachName() {
        return picAttachName;
    }

    /**
     * 
     * @param picAttachName 附件名
     */
    public void setPicAttachName(String picAttachName) {
        this.picAttachName = picAttachName == null ? null : picAttachName.trim();
    }

    /**
     * 
     * @return pic_attach_type 附件类型
     */
    public String getPicAttachType() {
        return picAttachType;
    }

    /**
     * 
     * @param picAttachType 附件类型
     */
    public void setPicAttachType(String picAttachType) {
        this.picAttachType = picAttachType == null ? null : picAttachType.trim();
    }

    /**
     * 
     * @return pic_attach_url 附件地址
     */
    public String getPicAttachUrl() {
        return picAttachUrl;
    }

    /**
     * 
     * @param picAttachUrl 附件地址
     */
    public void setPicAttachUrl(String picAttachUrl) {
        this.picAttachUrl = picAttachUrl == null ? null : picAttachUrl.trim();
    }

    /**
     * 
     * @return pic_attach_file_volume 附件大小
     */
    public String getPicAttachFileVolume() {
        return picAttachFileVolume;
    }

    /**
     * 
     * @param picAttachFileVolume 附件大小
     */
    public void setPicAttachFileVolume(String picAttachFileVolume) {
        this.picAttachFileVolume = picAttachFileVolume == null ? null : picAttachFileVolume.trim();
    }

    /**
     * 
     * @return pic_attach_file_type 附件类型 比如jpg png等
     */
    public String getPicAttachFileType() {
        return picAttachFileType;
    }

    /**
     * 
     * @param picAttachFileType 附件类型 比如jpg png等
     */
    public void setPicAttachFileType(String picAttachFileType) {
        this.picAttachFileType = picAttachFileType == null ? null : picAttachFileType.trim();
    }

    /**
     * 
     * @return pic_attach_owner 附件归属id
     */
    public String getPicAttachOwner() {
        return picAttachOwner;
    }

    /**
     * 
     * @param picAttachOwner 附件归属id
     */
    public void setPicAttachOwner(String picAttachOwner) {
        this.picAttachOwner = picAttachOwner == null ? null : picAttachOwner.trim();
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