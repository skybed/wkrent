package com.wkrent.business.app.base.service;

import java.util.List;

import com.wkrent.business.app.base.obj.RegionInfo;
import com.wkrent.common.entity.AppRegion;

public interface AppRegionService {
	
	public void insertAppRegin(AppRegion appRegion);

	public List<RegionInfo> getAllRegionList(String regionInfo);
	
	public List<RegionInfo> getAllRegionListByCharacter(String regionInfo);
}
