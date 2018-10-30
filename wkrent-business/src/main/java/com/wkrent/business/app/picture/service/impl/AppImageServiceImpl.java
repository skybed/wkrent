package com.wkrent.business.app.picture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.picture.dao.AppImageDao;
import com.wkrent.business.app.picture.service.AppImageService;
import com.wkrent.common.entity.BgPicAttach;

@Service
public class AppImageServiceImpl implements AppImageService {
	
	@Autowired
	private AppImageDao appImageDao;

	@Override
	public List<BgPicAttach> selectByOwnerId(String ownerId) {
		return appImageDao.selectByOwnerId(ownerId);
	}

	public void savePicAttach(BgPicAttach bgPicAttach) {
		appImageDao.insertSelective(bgPicAttach);
	}
}
