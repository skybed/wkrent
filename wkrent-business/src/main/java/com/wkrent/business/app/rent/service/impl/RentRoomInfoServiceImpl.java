package com.wkrent.business.app.rent.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrent.business.app.rent.dao.RentRoomDao;
import com.wkrent.business.app.rent.dao.RentRoomInfoDao;
import com.wkrent.business.app.rent.service.RentRoomInfoService;
import com.wkrent.common.entity.BgRoom;
import com.wkrent.common.entity.po.BgRoomInfo;

@Service
public class RentRoomInfoServiceImpl implements RentRoomInfoService {
	
	@Autowired
	private RentRoomDao rentRoomDao;
	
	@Autowired
	private RentRoomInfoDao rentRoomInfoDao;

	@Override
	@Transactional
	public void cancelAppoint(String roomId, String userId) {
		BgRoomInfo roomInfo = getRoomInfoByRoomId(roomId);
		
		BgRoomInfo info = new BgRoomInfo();
		info.setBgRoomInfoId(roomInfo.getBgRoomInfoId());
		info.setBgRoomAppointNum(roomInfo.getBgRoomAppointNum() - 1);
		rentRoomInfoDao.updateByPrimaryKeySelective(info);
		
		//判断是否需要修改房源状态
		BgRoom room = rentRoomDao.selectByPrimaryKey(roomId);
		if(room.getBgRoomMaxAppointNum() == roomInfo.getBgRoomAppointNum()) {
			//修改房源状态为 出租中
			BgRoom bgRoom = new BgRoom();
			bgRoom.setBgRoomId(room.getBgRoomId());
			bgRoom.setBgRoomStatus("1");
			bgRoom.setUpdateBy(userId);
			bgRoom.setUpdateTime(new Date());
			rentRoomDao.updateByPrimaryKeySelective(bgRoom);
		}
	}

	@Override
	public BgRoomInfo getRoomInfoByRoomId(String roomId) {
		return rentRoomInfoDao.selectByRoomId(roomId);
	}

	@Override
	@Transactional
	public void addViewNum(String roomId) {
		BgRoomInfo roomInfo = getRoomInfoByRoomId(roomId);
		
		BgRoomInfo info = new BgRoomInfo();
		info.setBgRoomInfoId(roomInfo.getBgRoomInfoId());
		info.setBgRoomViewNum(roomInfo.getBgRoomViewNum() + 1);
		rentRoomInfoDao.updateByPrimaryKeySelective(info);
	}

	@Override
	@Transactional
	public void AddAppoint(String roomId, String userId) {
		BgRoomInfo roomInfo = getRoomInfoByRoomId(roomId);
		
		BgRoomInfo info = new BgRoomInfo();
		info.setBgRoomInfoId(roomInfo.getBgRoomInfoId());
		info.setBgRoomAppointNum(roomInfo.getBgRoomAppointNum() + 1);
		rentRoomInfoDao.updateByPrimaryKeySelective(info);
	}

}
