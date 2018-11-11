package com.wkrent.common.entity.po;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

public class BgModule implements Serializable {

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
    private Clob bgModuleValue;


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

    public Clob getBgModuleValue() {
        return bgModuleValue;
    }

    public void setBgModuleValue(Clob bgModuleValue) {
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
}
