package com.wkrent.business.app.rent.service;

import java.util.List;

import com.wkrent.business.app.rent.obj.RentRoomCondition;
import com.wkrent.business.app.rent.obj.RoomInfo;
import com.wkrent.common.entity.BgRoom;

public interface RentRoomService {
	
	public Integer getRentRoomCount(RentRoomCondition condition);
	
	public List<RoomInfo> getRentRoomListByPager(RentRoomCondition condition);
	
	public BgRoom selectByPrimaryKey(String bgRoomId);

	public void updateRoomStatus(String roomId, String userId);
}
