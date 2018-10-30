/*
*
* AppPhoneCodeHistoryMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-28
*/
package com.wkrent.business.app.user.dao;

import org.apache.ibatis.annotations.Param;

import com.wkrent.common.entity.AppPhoneCodeHistory;

public interface AppPhoneCodeHistoryDao {

	int deleteByPrimaryKey(String phoneCodeHistoryId);

    int insert(AppPhoneCodeHistory record);

    int insertSelective(AppPhoneCodeHistory record);

    AppPhoneCodeHistory selectByPrimaryKey(String phoneCodeHistoryId);

    int updateByPrimaryKeySelective(AppPhoneCodeHistory record);

    int updateByPrimaryKey(AppPhoneCodeHistory record);
 
    int validatePhoneCode(@Param("historyId")String historyId);
    
}