package com.wkrent.business.app.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.user.dao.AppUserDao;
import com.wkrent.business.app.user.service.AppUserService;
import com.wkrent.common.entity.AppUser;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	private AppUserDao appUserDao;

	@Override
	public void insertAppUser(AppUser appUser) {
		appUserDao.insertSelective(appUser);
	}

	@Override
	public AppUser getUserByPhone(String phone) {
		return appUserDao.selectByUserPhone(phone);
	}

	@Override
	public AppUser getUserById(String userId) {
		return appUserDao.selectByPrimaryKey(userId);
	}

	@Override
	public void updateUser(AppUser appUser) {
		appUserDao.updateByPrimaryKeySelective(appUser);
	}

}
