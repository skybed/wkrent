/*
*
* BgDataDictValue.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.vo;

import com.wkrent.common.entity.paging.Page;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 数据字典
 * @author skybed
 *
 */
@ApiModel
public class BgDataDictValueVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5801113974059232853L;

    /**
     * 数据字典值id,唯一标识
     */
    private String bgDataDictValueId;

    /**
     * 数据字典type
     */
    private String bgDataDictId;

    /**
     * 数据字典类型
     */
    private String bgDataDictType;

    /**
     * 数据字典值
     */
    private String bgDataDictValue;

    /**
     * 是否删除 0-否1-是
     */
    private String isDelete;

    /**
     * 是否启用 0-否 1-是
     */
    private String isActive;

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
     * 枚举值idList
     */
    private List<String> dataDictValueIdList;

    /**
     * 分页信息
     */
    private Page page;

    /**
     * 
     * @return bg_data_dict_value_id 数据字典值id,唯一标识
     */
    public String getBgDataDictValueId() {
        return bgDataDictValueId;
    }

    /**
     * 
     * @param bgDataDictValueId 数据字典值id,唯一标识
     */
    public void setBgDataDictValueId(String bgDataDictValueId) {
        this.bgDataDictValueId = bgDataDictValueId == null ? null : bgDataDictValueId.trim();
    }


    /**
     * 
     * @return bg_data_dict_value 数据字典值
     */
    public String getBgDataDictValue() {
        return bgDataDictValue;
    }

    /**
     * 
     * @param bgDataDictValue 数据字典值
     */
    public void setBgDataDictValue(String bgDataDictValue) {
        this.bgDataDictValue = bgDataDictValue == null ? null : bgDataDictValue.trim();
    }

    /**
     * 
     * @return is_detele 是否删除 0-否1-是
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 
     * @param isDelete 是否删除 0-否1-是
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

    public String getBgDataDictType() {
        return bgDataDictType;
    }

    public void setBgDataDictType(String bgDataDictType) {
        this.bgDataDictType = bgDataDictType;
    }

    public List<String> getDataDictValueIdList() {
        return dataDictValueIdList;
    }

    public void setDataDictValueIdList(List<String> dataDictValueIdList) {
        this.dataDictValueIdList = dataDictValueIdList;
    }

    public String getBgDataDictId() {
        return bgDataDictId;
    }

    public void setBgDataDictId(String bgDataDictId) {
        this.bgDataDictId = bgDataDictId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}