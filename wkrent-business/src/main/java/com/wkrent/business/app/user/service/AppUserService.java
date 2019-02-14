package com.wkrent.business.app.user.service;

import com.wkrent.common.entity.AppUser;

public interface AppUserService {
	
	public void insertAppUser(AppUser appUser);
	
	public AppUser getUserByPhone(String phone);
	
	public AppUser getUserById(String userId);
	
	public void updateUser(AppUser appUser);
	
	public AppUser getUserByOpenId(String openId);

}
