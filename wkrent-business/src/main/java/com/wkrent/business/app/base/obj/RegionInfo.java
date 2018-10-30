package com.wkrent.business.app.base.obj;

import java.io.Serializable;

public class RegionInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3426135474195102887L;

	//地区id，主键
	private String regionId;
	
	//国家或地区名
	private String regionName;
	
	//国家或地区中文名
	private String regionCnName;
	
	//国家或地区码
	private String regionCode;
	
	//代号
	private String regionMark;
	
	//描述
	private String description;

	public RegionInfo() {
		super();
	}

	public RegionInfo(String regionId, String regionName, String regionCnName, String regionCode, String regionMark,
			String description) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.regionCnName = regionCnName;
		this.regionCode = regionCode;
		this.regionMark = regionMark;
		this.description = description;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionCnName() {
		return regionCnName;
	}

	public void setRegionCnName(String regionCnName) {
		this.regionCnName = regionCnName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionMark() {
		return regionMark;
	}

	public void setRegionMark(String regionMark) {
		this.regionMark = regionMark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
