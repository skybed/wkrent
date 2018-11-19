/*
*
* BgDataDict.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.common.entity.po;

import java.io.Serializable;

/**
 * 国家地区
 * @author skybed
 *
 */
public class BgArea implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5369227689370844939L;

    /**
     * 地区id,唯一标识
     */
    private String areaId;

    /**
     * 地区code
     */
    private String areaCode;

    /**
     * 地区名称
     */
    private String areaName;

    /**
     * 上级地区Code
     */
    private String areaParentCode;

    /**
     * 创建者
     */
    private String areaLevel;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaParentCode() {
        return areaParentCode;
    }

    public void setAreaParentCode(String areaParentCode) {
        this.areaParentCode = areaParentCode;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }
}