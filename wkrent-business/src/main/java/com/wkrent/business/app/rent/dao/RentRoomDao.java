package com.wkrent.business.app.rent.dao;

import java.util.List;

import com.wkrent.business.app.rent.obj.RentRoomCondition;
import com.wkrent.common.entity.BgRoom;

public interface RentRoomDao {

	Integer getRentRoomCount(RentRoomCondition condition);
	
	List<BgRoom> getRentRoomListByPager(RentRoomCondition condition);
	
	BgRoom selectByPrimaryKey(String bgRoomId);

	int updateByPrimaryKeySelective(BgRoom record);
	
}
