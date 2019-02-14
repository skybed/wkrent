/*
*
* AppUserMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.app.user.dao;

import com.wkrent.common.entity.AppUser;

public interface AppUserDao {

	int deleteByPrimaryKey(String appUserId);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(String appUserId);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);
    
    AppUser selectByUserPhone(String phone);
    
    AppUser selectByOpenId(String openId);
}