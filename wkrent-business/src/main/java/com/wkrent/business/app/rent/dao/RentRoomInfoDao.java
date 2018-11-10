/*
*
* BgRoomInfoMapper.java
* Copyright(C) 2017-2018 skybed
* @date 2018-10-21
*/
package com.wkrent.business.app.rent.dao;

import com.wkrent.common.entity.po.BgRoomInfo;

public interface RentRoomInfoDao {
	
    int deleteByPrimaryKey(String bgRoomInfoId);

    int insert(BgRoomInfo record);

    int insertSelective(BgRoomInfo record);

    BgRoomInfo selectByPrimaryKey(String bgRoomInfoId);

    int updateByPrimaryKeySelective(BgRoomInfo record);

    int updateByPrimaryKey(BgRoomInfo record);
    
    BgRoomInfo selectByRoomId(String roomId);
}