package com.wkrent.business.app.rent.service;

import com.wkrent.common.entity.po.BgRoomInfo;

public interface RentRoomInfoService {
	
	public BgRoomInfo getRoomInfoByRoomId(String roomId);
	
	public void cancelAppoint(String roomId, String userId);

	public void addViewNum(String roomId);
	
	public void AddAppoint(String roomId, String userId);
	
}
