package com.wkrent.business.app.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.base.dao.AppRegionDao;
import com.wkrent.business.app.base.obj.RegionInfo;
import com.wkrent.business.app.base.service.AppRegionService;
import com.wkrent.common.entity.AppRegion;

@Service
public class AppRegionServiceImpl implements AppRegionService {
	
	@Autowired
	private AppRegionDao appRegionDao;

	@Override
	public void insertAppRegin(AppRegion appRegion) {
		appRegionDao.insert(appRegion);
	}

	@Override
	public List<RegionInfo> getAllRegionList(String regionInfo) {
		List<AppRegion> regions = appRegionDao.getAllRegionList(regionInfo);
		List<RegionInfo> regionInfos = new ArrayList<RegionInfo>();
		for(AppRegion region : regions) {
			RegionInfo info = new RegionInfo();
			info.setRegionId(region.getRegionId());
			info.setRegionName(region.getRegionName());
			info.setRegionCode(region.getRegionCode());
			info.setRegionMark(region.getRegionMark());
			info.setRegionCnName(region.getRegionCnName());
			info.setDescription(region.getDescription());
			regionInfos.add(info);
		}
		return regionInfos;
	}

	@Override
	public List<RegionInfo> getAllRegionListByCharacter(String regionInfo) {
		List<AppRegion> regions = appRegionDao.getAllRegionList(regionInfo);
		List<RegionInfo> regionInfos = new ArrayList<RegionInfo>();
		for(AppRegion region : regions) {
			RegionInfo info = new RegionInfo();
			info.setRegionId(region.getRegionId());
			info.setRegionName(region.getRegionName());
			info.setRegionCode(region.getRegionCode());
			info.setRegionMark(region.getRegionMark());
			info.setRegionCnName(region.getRegionCnName());
			info.setDescription(region.getDescription());
			regionInfos.add(info);
		}
		return regionInfos;
	}

}
