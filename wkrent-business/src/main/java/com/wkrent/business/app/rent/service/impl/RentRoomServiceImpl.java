package com.wkrent.business.app.rent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkrent.business.app.rent.dao.AppAttentionDao;
import com.wkrent.business.app.rent.dao.RentRoomDao;
import com.wkrent.business.app.rent.obj.RentRoomCondition;
import com.wkrent.business.app.rent.obj.RoomInfo;
import com.wkrent.business.app.rent.service.RentRoomService;
import com.wkrent.common.entity.AppAttention;
import com.wkrent.common.entity.BgRoom;

@Service
public class RentRoomServiceImpl implements RentRoomService {
	
	@Autowired
	private RentRoomDao rentRoomDao;

	@Autowired
	private AppAttentionDao appAttentionDao;
	
	@Override
	public Integer getRentRoomCount(RentRoomCondition condition) {
		return rentRoomDao.getRentRoomCount(condition);
	}
	
	@Override
	public List<RoomInfo> getRentRoomListByPager(RentRoomCondition condition) {
		List<RoomInfo> infos = new ArrayList<RoomInfo>();
		if(condition != null && condition.getCurrentIndex() != null && condition.getPageSize() != null) {
			condition.setStartIndex((condition.getCurrentIndex() - 1) * condition.getPageSize());
			condition.setEndIndex(condition.getCurrentIndex() * condition.getPageSize());
			
			List<BgRoom> roomInfos = rentRoomDao.getRentRoomListByPager(condition);
			
			if(roomInfos != null && roomInfos.size() > 0) {
				for(int i = 0; i < roomInfos.size(); i++) {
					RoomInfo roomInfo = new RoomInfo();
					roomInfo.setRoomId(roomInfos.get(i).getBgRoomId());
					roomInfo.setRoomName(roomInfos.get(i).getBgRoomName());
					roomInfo.setAddressCountry(roomInfos.get(i).getBgRoomAddressCountry());
					roomInfo.setAddressCity(roomInfos.get(i).getBgRoomAddressCity());
					roomInfo.setAddressDetail(roomInfos.get(i).getBgRoomAddressDetail());
					roomInfo.setPrice(roomInfos.get(i).getBgRoomPrice() + "/" + getPriceUnit(roomInfos.get(i).getBgRoomPriceUnit()));
					roomInfo.setStatus(roomInfos.get(i).getBgRoomStatus());
					roomInfo.setRoomTips(roomInfos.get(i).getBgRoomTips());
					roomInfo.setAddressCountry(roomInfos.get(i).getBgRoomAddressCountry());
					roomInfo.setAddressCity(roomInfos.get(i).getBgRoomAddressCity());
					roomInfo.setAddressDetail(roomInfos.get(i).getBgRoomAddressDetail());
					
					//判断是否被收藏
					AppAttention appAttention = appAttentionDao.selectByRoomAndUserId(roomInfos.get(i).getBgRoomId(), condition.getUserId());
					if(appAttention == null) {
						roomInfo.setIsAttention("0");
					} else {
						roomInfo.setIsAttention("1");
					}
					
					infos.add(roomInfo);
				}
			}
		}
		return infos;
	}
	
	public String getPriceUnit(String unit) {
		if("0".equals(unit)) {
			return "周";
		} else if("1".equals(unit)) {
			return "月";
		} else if ("2".equals(unit)) {
			return "年";
		}
		return "";
	}

	@Override
	public BgRoom selectByPrimaryKey(String bgRoomId) {
		return rentRoomDao.selectByPrimaryKey(bgRoomId);
	}

	@Override
	public void updateRoomStatus(String roomId, String userId) {
		BgRoom room = new BgRoom();
		room.setBgRoomStatus("2");
		room.setUpdateBy(userId);
		room.setUpdateTime(new Date());
		rentRoomDao.updateByPrimaryKeySelective(room);
	}

}
